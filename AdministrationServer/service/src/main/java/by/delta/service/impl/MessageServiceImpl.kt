package by.delta.service.impl

import by.delta.converter.impl.MessageConverter
import by.delta.dto.MessageDto
import by.delta.dto.UserDto
import by.delta.exception.MessageError
import by.delta.exception.ModelSameServiceException
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.model.Message
import by.delta.repository.IRepository
import by.delta.service.IFaceService
import by.delta.service.IMessageService
import by.delta.specification.impl.message.GetUserMessageByMessageId
import by.delta.specification.impl.message.GetUserMessagesByFaceId
import by.delta.specification.impl.message.countSpecification.GetCountOfMessage
import by.delta.util.ConstParamService
import by.delta.validator.paramsvalidator.abstractvalidator.PagingParamsValidator
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
import java.time.LocalDate


@Service
open class MessageServiceImpl @Autowired constructor(private val messageConverter: MessageConverter,
                                                     private val messageValidator: MessageValidator,
                                                     private val messageRepository: IRepository<Message>,
                                                     private val faceService: IFaceService,
                                                     private val messageParametersValidator: MessageParametersValidator,
                                                     private val authenticationValidator: AuthenticationValidator
) : IMessageService {

    @Transactional
    override fun createMessage(authentication: Authentication?, messageDto: MessageDto): MessageDto {
        authenticationValidator.validate(authentication)
        messageValidator.validate(messageDto)
        messageDto.createDate = LocalDate.now()
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
        messageValidator.checkId(id)
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
            throw MessageError(ServiceErrorCode.MESSAGE_USER_NOT_EXIST, ConstParamService.MESSAGE_ID_STRING)
        }
        return messageConverter.modelToDtoList(messages)

    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(MessageServiceImpl::class.java)
    }
}