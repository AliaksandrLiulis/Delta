package by.delta.service.impl

import by.delta.converter.impl.MessageConverter
import by.delta.dto.FaceDto
import by.delta.dto.MessageDto
import by.delta.exception.MessageError
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.model.Message
import by.delta.repository.IRepository
import by.delta.service.IFaceService
import by.delta.service.IIncomingService
import by.delta.service.IMessageService
import by.delta.specification.impl.message.GetMessageByMessageIdAndFaceId
import by.delta.specification.impl.message.GetUserMessageByMessageId
import by.delta.specification.impl.message.GetUserMessagesByFaceId
import by.delta.specification.impl.message.countSpecification.GetCountOfMessage
import by.delta.specification.impl.message.countSpecification.GetMessageByMessageId
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
    private lateinit var incomingService: IIncomingService

    @Transactional
    override fun createMessage(authentication: Authentication?, messageDto: MessageDto): MessageDto {
        authenticationValidator.validate(authentication)
        messageValidator.validate(messageDto)
        messageDto.createDate = LocalDateTime.now()
        messageDto.faceDto = faceService.getFaceByUserEmail(authentication!!.name)

        val setFaces = HashSet<FaceDto>()
        val responseMessage = messageConverter.modelToDto(messageRepository.save(messageConverter.dtoToModel(messageDto)))

        if (messageDto.recipients != null) {
            val map = HashMap<String, Any>()
            val list = ArrayList<String>()
            for (face in messageDto.recipients) {
                if (face.id != null) {
                    list.add(face.id.toString())
                }
            }
            map["face_id"] = list
            map["message_id"] = responseMessage.id
            var recipientsDto = incomingService.createIncoming(map)

            if (!CollectionUtils.isEmpty(recipientsDto)) {
                for (recipient in recipientsDto) {
                    setFaces.add(faceService.getFaceByUserId(recipient.faceDto!!.id))
                }
            }
        }
        if (!CollectionUtils.isEmpty(setFaces)) {
            responseMessage.recipients = setFaces
        }
        return responseMessage
    }

    @Transactional
    override fun updateMessage(authentication: Authentication?, id: Long, messageDto: MessageDto): MessageDto {
        val messages = getModelExistMessage(authentication, id)
        var existMessage = messages.elementAt(0)
        val setFaces = HashSet<FaceDto>()
        if (existMessage.sendDate != null) {
            LOGGER.error("Message has already been sent and can't be changing")
            throw MessageError(ServiceErrorCode.MESSAGE_WAS_SEND_AND_CAN_NOT_BE_CHANGED, "")
        }
        if (!StringUtils.isEmpty(messageDto.messageSubject)) {
            messageValidator.checkMessageSubject(messageDto)
            existMessage.messageSubject = messageDto.messageSubject
        }
        if (!StringUtils.isEmpty(messageDto.messageBody)) {
            existMessage.messageBody = messageDto.messageBody
        }
        if (messageDto.recipients != null) {
            val map = HashMap<String, Any>()
            val list = ArrayList<String>()
            for (face in messageDto.recipients) {
                if (face.id != null) {
                    list.add(face.id.toString())
                }
            }
            map["face_id"] = list
            map["message_id"] = existMessage.id
            var recipientsDto = incomingService.createIncoming(map)
            if (!CollectionUtils.isEmpty(recipientsDto)) {
                for (recipient in recipientsDto) {
                    setFaces.add(faceService.getFaceByUserId(recipient.faceDto!!.id))
                }
            }
        }

        var responseMessage = messageConverter.modelToDto(messageRepository.update(existMessage))

        if (!CollectionUtils.isEmpty(setFaces)) {
            responseMessage.recipients = setFaces
        }
        return responseMessage
    }

    override fun getUserMessages(authentication: Authentication?, allRequestParams: MutableMap<String, String>): Map<String, Any> {
        authenticationValidator.validate(authentication)
        //Get user by authentication
        val face = faceService.getModelFaceByUserEmail(authentication!!.name)
        //Create new params and validate old
        var newParams = messageParametersValidator.validate(allRequestParams)
        //Add Id User to new parameters
        val list = listOf(face.user.id.toString())
        newParams[ConstParamService.ID] = list
        //Get count of records
        val countOfMessages = messageRepository.countQuery(GetCountOfMessage(newParams))
        //Get records
        val messages = messageRepository.query(GetUserMessagesByFaceId(newParams),
                allRequestParams.getValue(ConstParamService.LIMIT).toInt(), allRequestParams.getValue(ConstParamService.OFFSET).toInt()).toSet()
        messages.forEach { message: Message -> message.messageBody = null }
        var messDto = messageConverter.modelToDtoList(messages)
        addRecipientToMessages(messDto)
        var mapParams = LinkedHashMap<String, Any>()
        mapParams[ConstParamService.COUNT_STRING] = countOfMessages.toString()
        mapParams[ConstParamService.RECORDS_STRING] = messDto
        return mapParams
    }

    private fun addRecipientToMessages(messDto: Set<MessageDto>) {
        for (mess in messDto) {
            val setInc = incomingService.getIncomingByMessageId(mess.id)
            var setName = LinkedHashSet<FaceDto>()

            for (s in setInc) {
                val face = faceService.getFaceById(s.face.id)
                setName.add(face)

            }
            mess.recipients = setName
        }
    }


    override fun getMessageByIdMessageForUser(authentication: Authentication?, id: Long): MessageDto {
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
        var messDto = messageConverter.modelToDtoList(messages)
        addRecipientToMessages(messDto)
        return messDto.toList()[0]
    }

    override fun getIncomingModelMessageByUserId(id: Long): Message {
        var newParams: MutableMap<String, List<String>> = Helper.getWraperId(id)
        //Get records
        val messages = messageRepository.query(GetMessageByMessageId(newParams), 1, 0)
        //Generate message if messages for user not exist
        if (CollectionUtils.isEmpty(messages)) {
            LOGGER.error("Messages for this user not exist")
            throw MessageError(ServiceErrorCode.MESSAGE_ID_NOT_EXIST, ConstParamService.MESSAGE_ID_STRING)
        }

        return messages[0]
    }

    override fun checkAndGetMessageById(id: Long): MessageDto {
        messageValidator.checkId(id.toString())
        val messages = messageRepository.query(GetMessageByMessageIdAndFaceId(Helper.getWraperId(id)), 1, 0)
        if (CollectionUtils.isEmpty(messages)) {
            LOGGER.error("Messages with such id not exist")
            throw MessageError(ServiceErrorCode.MESSAGE_ID_NOT_EXIST, ConstParamService.MESSAGE_ID_STRING)
        }
        return messageConverter.modelToDto(messages[0])
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
        for (inc in incomingMessage) {
            inc.messageState = 1
            incomingService.updateIncoming(inc)
        }
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