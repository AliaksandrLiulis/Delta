package by.delta.service.impl

import by.delta.converter.impl.MessageConverter
import by.delta.dto.MessageDto
import by.delta.exception.MessageError
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.model.Message
import by.delta.repository.IRepository
import by.delta.service.IFaceService
import by.delta.service.IIncomingService
import by.delta.service.IMessageService
import by.delta.specification.impl.message.GetMessageById
import by.delta.specification.impl.message.GetUserMessageByMessageId
import by.delta.specification.impl.message.GetUserMessagesByFaceId
import by.delta.specification.impl.message.countSpecification.GetCountOfMessage
import by.delta.util.ConstParamService
import by.delta.util.Helper
import by.delta.validator.AuthenticationValidator
import by.delta.validator.MessageValidator
import by.delta.validator.paramsvalidator.MessageParametersValidator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.CollectionUtils
import org.springframework.util.StringUtils
import java.time.LocalDateTime

@Service
open class MessageServiceImpl @Autowired constructor(private val messageConverter: MessageConverter,
                                                     private val messageValidator: MessageValidator,
                                                     private val messageRepository: IRepository<Message>,
                                                     private val faceService: IFaceService,
                                                     private val messageParametersValidator: MessageParametersValidator,
                                                     private val authenticationValidator: AuthenticationValidator

) : IMessageService {

    @Autowired
    private lateinit var incomingService:IIncomingService

    @Transactional
    override fun createMessage(authentication: Authentication?, messageDto: MessageDto): MessageDto {
        authenticationValidator.validate(authentication)
        messageValidator.validate(messageDto)
        messageDto.createDate = LocalDateTime.now()
        messageDto.faceDto = faceService.getFaceByUserEmail(authentication!!.name)
        return messageConverter.modelToDto(messageRepository.save(messageConverter.dtoToModel(messageDto)))
    }

    override fun getUserMessages(authentication: Authentication?, allRequestParams: MutableMap<String, String>): Map<String, Any> {
        authenticationValidator.validate(authentication)
        //Get user by authentication
        val faceDto = faceService.getFaceByUserEmail(authentication!!.name)
        //Create new params and validate old
        var newParams = messageParametersValidator.validate(allRequestParams)
        //Add Id User to new parameters
        val list = listOf(faceDto.id.toString())
        newParams[ConstParamService.ID] = list
        //Get count of records
        val countOfMessages = messageRepository.countQuery(GetCountOfMessage(newParams))
        //Get records
        val messages = messageRepository.query(GetUserMessagesByFaceId(newParams),
                allRequestParams.getValue(ConstParamService.LIMIT).toInt(), allRequestParams.getValue(ConstParamService.OFFSET).toInt()).toSet()
        //add count and records to response
        var mapParams = LinkedHashMap<String, Any>()
        mapParams[ConstParamService.COUNT_STRING] = countOfMessages.toString()
        mapParams[ConstParamService.RECORDS_STRING] = messageConverter.modelToDtoList(messages)
        return mapParams
    }

    override fun getMessageById(authentication: Authentication?, id: Long): Set<MessageDto> {
        authenticationValidator.validate(authentication)
        messageValidator.checkId(id.toString())
        //Get user by authentication
        val faceDto = faceService.getFaceByUserEmail(authentication!!.name)
        //Create new params with message Id
        var newParams: MutableMap<String, List<String>> = Helper.getWraperId(id)
        //Add Id User to new parameters
        val list = listOf(faceDto.id.toString())
        newParams[ConstParamService.FACE_ID] = list
        //Get records
        val messages = messageRepository.query(GetUserMessageByMessageId(newParams), 1, 0).toSet()
        //Generate message if messages for user not exist
        if (CollectionUtils.isEmpty(messages)) {
            LOGGER.error("Messages for this user not exist")
            throw MessageError(ServiceErrorCode.MESSAGE_ID_NOT_EXIST, ConstParamService.MESSAGE_ID_STRING)
        }
        return messageConverter.modelToDtoList(messages)
    }

    override fun checkAndGetMessageById(id: Long): MessageDto {
        messageValidator.checkId(id.toString())
        val messages = messageRepository.query(GetMessageById(Helper.getWraperId(id)), 1, 0)
        if (CollectionUtils.isEmpty(messages)) {
            LOGGER.error("Messages with such id not exist")
            throw MessageError(ServiceErrorCode.MESSAGE_ID_NOT_EXIST, ConstParamService.MESSAGE_ID_STRING)
        }
        return messageConverter.modelToDto(messages[0])
    }

    @Transactional
    override fun updateMessage(authentication: Authentication?, id: Long, messageDto: MessageDto): MessageDto {
        val messages = getModelExistMessage(authentication, id)
        var existMessage = messages.elementAt(0)
        if (!StringUtils.isEmpty(messageDto.messageSubject)) {
            messageValidator.checkMessageSubject(messageDto)
            existMessage.messageSubject = messageDto.messageSubject
        }
        if (!StringUtils.isEmpty(messageDto.messageBody)) {
            existMessage.messageBody = messageDto.messageBody
        }
        return messageConverter.modelToDto(messageRepository.update(existMessage))
    }

    @Transactional
    override fun sendMessage(authentication: Authentication?, idMessage: Long) {
        val existMessage = getModelExistMessage(authentication, idMessage).elementAt(0)
        var incomingMessage = incomingService.getIncomingByMessageId(existMessage.id)
        if (CollectionUtils.isEmpty(incomingMessage)) {
            LOGGER.error("Can't send message. Recipient not exist")
            throw MessageError(ServiceErrorCode.RECIPIENT_FOR_MESSAGE_NOT_EXIST, "")
        }
        if (existMessage.sendDate != null) {
            LOGGER.error("Message has already been sent")
            throw MessageError(ServiceErrorCode.MESSAGE_WAS_SEND, "")
        }
        existMessage.sendDate = LocalDateTime.now()
        messageRepository.update(existMessage)
        var changedInc = incomingMessage[0]
        changedInc.messageState = 1
        incomingService.updateIncoming(changedInc)
    }

    private fun getModelExistMessage(authentication: Authentication?, id: Long): Set<Message> {
        authenticationValidator.validate(authentication)
        messageValidator.checkId(id.toString())
        val faceDto = faceService.getFaceByUserEmail(authentication!!.name)
        var newParams: MutableMap<String, List<String>> = Helper.getWraperId(id)
        val list = listOf(faceDto.id.toString())
        newParams[ConstParamService.FACE_ID] = list
        val messages = messageRepository.query(GetUserMessageByMessageId(newParams), 1, 0).toSet()
        if (CollectionUtils.isEmpty(messages)) {
            LOGGER.error("Messages for this user not exist")
            throw MessageError(ServiceErrorCode.MESSAGE_ID_NOT_EXIST, ConstParamService.MESSAGE_ID_STRING)
        }
        return messages
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(MessageServiceImpl::class.java)
    }
}