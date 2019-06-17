package by.delta.service

import by.delta.dto.MessageDto
import by.delta.model.Message
import org.springframework.security.core.Authentication
import sun.plugin2.message.RemoteCAContextIdMessage

interface IMessageService {

    fun createMessage(authentication: Authentication?, messageDto: MessageDto): MessageDto
    fun getUserMessages(authentication: Authentication?, allRequestParams: MutableMap <String,String>): Map<String, Any>
    fun getMessageByIdMessageForUser(authentication: Authentication?, id:Long): MessageDto
    fun getIncomingModelMessageByUserId(id:Long): Message
    fun checkAndGetMessageById(id:Long): MessageDto
    fun updateMessage(authentication: Authentication?, id:Long, messageDto: MessageDto): MessageDto
    fun sendMessage(authentication: Authentication?, idMessage: Long)
}
