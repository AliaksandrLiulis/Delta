package by.delta.service.impl

import by.delta.converter.impl.IncomingConverter
import by.delta.dto.IncomingDto
import by.delta.dto.MessageDto
import by.delta.exception.MessageError
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.model.Incoming
import by.delta.repository.IRepository
import by.delta.service.IFaceService
import by.delta.service.IIncomingService
import by.delta.service.IMessageService
import by.delta.service.IUserService
import by.delta.specification.impl.incoming.GetIncomingByIdMessage
import by.delta.specification.impl.incoming.GetIncomingByIdFace
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
                                                      private val userValidator: UserValidator

) : IIncomingService {

    @Autowired
    private lateinit var messageService: IMessageService

    override fun getUserIncoming(authentication: Authentication?, allRequestParams: MutableMap<String, String>): Map<String, Any> {
        authenticationValidator.validate(authentication)
        val faceDto = faceService.getFaceByUserEmail(authentication!!.name)
        val x = incomingRepository.query(GetIncomingByIdFace(Helper.getWraperId(faceDto.id)),100,0).toSet()
        var mapParams = LinkedHashMap<String, Any>()
        mapParams[ConstParamService.RECORDS_STRING] = incomingConverter.modelToDtoList(x)
        return mapParams
    }

    @Transactional
    override fun createIncoming(authentication: Authentication?, resource: MutableMap<String, Any>): List<IncomingDto> {
        authenticationValidator.validate(authentication)
        userService.checkAndGetUserByEmail(authentication!!.name)
        val responseList = ArrayList<IncomingDto>()

        //Same data removed
        val recipients = removeSameData(checkArrayAndGetList(resource))
        val message = resource["message_id"]
        messageValidator.checkId(message.toString())

        //Get exist message by message Id
        val existMessage = messageService.checkAndGetMessageById(message.toString().toLong())
        if (existMessage.sendDate != null) {
            LOGGER.error("Message has already been sent")
            throw MessageError(ServiceErrorCode.MESSAGE_WAS_SEND, "")
        }

        //Get exist incoming message by message Id
        val listIncomingMessage = incomingRepository.query(GetIncomingByIdMessage(Helper.getWraperId(existMessage.id)), 1000, 0)

        //add Incoming records if table for message is empty
        if (CollectionUtils.isEmpty(listIncomingMessage)) {
            addIncoming(responseList, recipients, existMessage)
            return responseList
        }
        //Copy of lists for remove and add message
        var listForRemoveMessages: ArrayList<Incoming> = ArrayList(listIncomingMessage)
        var listForAddMessages: ArrayList<Int> = ArrayList(recipients)
        createRemoveAndAddMessageList(responseList, listForRemoveMessages, listForAddMessages, listIncomingMessage, recipients)
        //remove Incoming records
        listForRemoveMessages.forEach(Consumer { t ->
            if (t.messageState == 0) {
                incomingRepository.remove(t.id)
            }
        })
        //add Incoming records
        addIncoming(responseList, listForAddMessages, existMessage)
        return responseList
    }

    override fun getIncomingByMessageId(messageId: Long): List<Incoming> {
        return incomingRepository.query(GetIncomingByIdMessage(Helper.getWraperId(messageId)), 100, 0)
    }

    override fun updateIncoming(incoming: Incoming): Incoming {
        return incomingRepository.update(incoming)
    }

    private fun addIncoming(resultList: ArrayList<IncomingDto>, listForAddMessages: List<Int>, existMessage: MessageDto) {
        listForAddMessages.forEach(Consumer { t ->
            var incomingDto = IncomingDto()
            val existUser = userService.getUserById(t.toLong())
            val faceExistUser = faceService.getFaceByUserId(existUser.id)
            incomingDto.messageState = 0
            incomingDto.messageDto = existMessage
            incomingDto.faceDto = faceExistUser
            var savedIncomingDto = incomingConverter.modelToDto(incomingRepository.save(incomingConverter.dtoToModel(incomingDto)))
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
        return list
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
                    resultList.add(incomingConverter.modelToDto(message))
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