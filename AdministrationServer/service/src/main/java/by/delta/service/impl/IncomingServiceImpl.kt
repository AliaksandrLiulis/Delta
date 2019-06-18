package by.delta.service.impl

import by.delta.converter.impl.FaceConverter
import by.delta.converter.impl.IncomingConverter
import by.delta.converter.impl.MessageConverter
import by.delta.dto.IncomingDto
import by.delta.dto.MessageDto
import by.delta.exception.MessageError
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.model.Incoming
import by.delta.model.Message
import by.delta.repository.IRepository
import by.delta.repository.impl.MessageRepositoryImpl
import by.delta.service.IFaceService
import by.delta.service.IIncomingService
import by.delta.service.IMessageService
import by.delta.service.IUserService
import by.delta.specification.impl.incoming.GetIncomingByFaceIdAndMessageId
import by.delta.specification.impl.incoming.GetIncomingById
import by.delta.specification.impl.incoming.GetIncomingByIdFace
import by.delta.specification.impl.incoming.countSpecification.GetCountOfIncomingMessage
import by.delta.specification.impl.message.GetIncomingMessages
import by.delta.specification.impl.message.GetMessageByMessageId
import by.delta.specification.impl.message.GetSentMessage
import by.delta.specification.impl.message.GetUserMessageByMessageIdAndFaceId
import by.delta.util.ConstParamService
import by.delta.util.Helper
import by.delta.validator.AuthenticationValidator
import by.delta.validator.MessageValidator
import by.delta.validator.UserValidator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.CollectionUtils
import java.util.function.Consumer

@Service
open class IncomingServiceImpl @Autowired constructor(private val incomingRepository: IRepository<Incoming>,
                                                      private val incomingConverter: IncomingConverter,
                                                      private val authenticationValidator: AuthenticationValidator,
                                                      private val userService: IUserService,
                                                      private val faceService: IFaceService,
                                                      private val messageValidator: MessageValidator,
                                                      private val userValidator: UserValidator,
                                                      private val messageConverter: MessageConverter,
                                                      private val faceConverter: FaceConverter,
                                                      private val messageRepository: IRepository<Message>

) : IIncomingService {

    @Autowired
    private lateinit var messageService: IMessageService

    override fun getUserIncoming(authentication: Authentication?, allRequestParams: MutableMap<String, String>): Map<String, Any> {
        authenticationValidator.validate(authentication)
        val faceDto = faceService.getModelFaceByUserEmail(authentication!!.name)
        val list = listOf(faceDto.user!!.id.toString())
        var newParams = HashMap<String, List<String>>()
        newParams[ConstParamService.ID] = list

        val countOfIncoming = incomingRepository.countQuery(GetCountOfIncomingMessage(newParams))
        val allIncoming = incomingRepository.query(GetIncomingByIdFace(Helper.getWraperId(faceDto.id)), 100, 0).toSet()


        var result = HashSet<IncomingDto>()
        for (incoming in allIncoming) {
            val mess = messageService.getIncomingModelMessageByUserId(incoming.id)
            val sentFace = mess.face
            var realmessage = messageConverter.modelToDto(mess)
            realmessage.recipients = null
            realmessage.messageBody = null
            realmessage.createDate = null
            realmessage.faceDto = faceConverter.modelToDto(sentFace)
            var incDto = incomingConverter.modelToDto(incoming)
            incDto.messageState = null
            incDto.messageDto = realmessage
            result.add(incDto)
        }

        var mapParams = LinkedHashMap<String, Any>()
        mapParams[ConstParamService.COUNT_STRING] = countOfIncoming.toString()
        mapParams[ConstParamService.RECORDS_STRING] = result
        return mapParams
    }

    @Transactional
    override fun createIncoming(resource: MutableMap<String, Any>): List<IncomingDto> {
        val responseList = ArrayList<IncomingDto>()
        //Same data removed
        val recipients = checkArrayAndGetList(resource)

        val message = resource["message_id"]
        messageValidator.checkId(message.toString())

        //Get exist message by message Id
        val existMessage = messageService.checkAndGetMessageById(message.toString().toLong())

        if (existMessage.sendDate != null) {
            LOGGER.error("Message has already been sent")
            throw MessageError(ServiceErrorCode.MESSAGE_WAS_SEND, "")
        }

        //Get exist incoming message by message Id
        val listIncomingMessage = incomingRepository.query(GetIncomingById(Helper.getWraperId(existMessage.id)), 1000, 0)

        //add Incoming records if table for message is empty
        if (CollectionUtils.isEmpty(listIncomingMessage)) {
            addIncoming(responseList, recipients, existMessage)
            return responseList
        }

        //Copy of lists for remove and add message
        var listForRemoveMessages: ArrayList<Incoming> = ArrayList(listIncomingMessage)
        var listForAddMessages: ArrayList<Int> = ArrayList(recipients)
        createRemoveAndAddMessageList(responseList, listForRemoveMessages, listForAddMessages, listIncomingMessage, recipients)

        listForRemoveMessages.forEach(Consumer { t ->
            if (t.messageState == 0) {
                incomingRepository.remove(t.id)
            }
        })
        //add Incoming records

        addIncoming(responseList, listForAddMessages, existMessage)


        return responseList
    }

    override fun getIncomingById(messageId: Long): List<Incoming> {
        return incomingRepository.query(GetIncomingById(Helper.getWraperId(messageId)), 1, 0)
    }

    override fun getIncomingByMessageId(authentication: Authentication?, messageId: Long): MessageDto {
        authenticationValidator.validate(authentication)
        val face = faceService.getModelFaceByUserEmail(authentication!!.name)
        var newParams: MutableMap<String, List<String>> = Helper.getWraperId(messageId)
        val list = listOf(face.id.toString())
        newParams[ConstParamService.FACE_ID] = list
        val incoming = incomingRepository.query(GetIncomingByFaceIdAndMessageId(newParams),1,0)
        if (CollectionUtils.isEmpty(incoming)) {
            LOGGER.error("Messages for this user not exist")
            throw MessageError(ServiceErrorCode.MESSAGE_ID_NOT_EXIST, ConstParamService.MESSAGE_ID_STRING)
        }
        val messages = messageRepository.query(GetIncomingMessages(Helper.getWraperId(messageId)), 1, 0)
        if (CollectionUtils.isEmpty(messages)) {
            LOGGER.error("Messages for this user not exist")
            throw MessageError(ServiceErrorCode.MESSAGE_ID_NOT_EXIST, ConstParamService.MESSAGE_ID_STRING)
        }
        var whosent = messages[0].face
        val messageDto = messageConverter.modelToDto(messages[0])
        messageDto.faceDto = faceConverter.modelToDto(whosent)
        return messageDto
    }

    override fun updateIncoming(incoming: Incoming): Incoming {
        return incomingRepository.update(incoming)
    }

    private fun addIncoming(resultList: ArrayList<IncomingDto>, listForAddMessages: List<Int>, existMessage: MessageDto) {
        listForAddMessages.forEach(Consumer { t ->
            var incomingDto = IncomingDto()
            val existUser = userService.getUserById(t.toLong())
            val existRecipientUser = faceService.getFaceByUserId(existUser.id)
            incomingDto.messageState = 0
            incomingDto.messageDto = existMessage
            incomingDto.faceDto = existRecipientUser
            var savedIncomingDto = incomingConverter.modelToDto(incomingRepository.save(incomingConverter.dtoToModel(incomingDto)))
            savedIncomingDto.faceDto = existRecipientUser
            resultList.add(savedIncomingDto)
        })
    }

    private fun removeSameData(list: List<Int>): List<Int> {
        val set = HashSet<Int>(list)
        return ArrayList<Int>(set)
    }

    private fun checkArrayAndGetList(resource: MutableMap<String, Any>): List<Int> {
        val temp: List<Any> = resource["face_id"] as List<Any>
        temp.forEach { any -> userValidator.checkId(any.toString()) }
        val list = ArrayList<Int>()
        temp.forEach { t: Any -> list.add(Integer.parseInt(t.toString())) }
        return removeSameData(list)
    }

    private fun createRemoveAndAddMessageList(resultList: ArrayList<IncomingDto>,
                                              listForRemoveMessages: ArrayList<Incoming>,
                                              listForAddMessages: ArrayList<Int>,
                                              listIncomingMessage: List<Incoming>,
                                              recipients: List<Int>
    ) {
        listIncomingMessage.forEach { message ->
            recipients.forEach { recipient ->
                if (recipient.toLong() == message.face.id) {
                    val existRecipientUser = faceService.getFaceByUserId(recipient.toLong())
                    var incomingMessage = incomingConverter.modelToDto(message)
                    incomingMessage.faceDto = existRecipientUser
                    resultList.add(incomingMessage)
                    listForRemoveMessages.remove(message)
                    listForAddMessages.remove(recipient)
                }
            }
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(IncomingServiceImpl::class.java)
    }
}