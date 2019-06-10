package by.delta.service

import by.delta.dto.MessageDto
import org.springframework.security.core.Authentication
import sun.plugin2.message.RemoteCAContextIdMessage

interface IMessageService {

    fun createMessage(authentication: Authentication?, messageDto: MessageDto): MessageDto
    fun getUserMessages(authentication: Authentication?, allRequestParams: MutableMap <String,String>): Map<String, Any>
    fun getMessageById(authentication: Authentication?, id:Long): Set<MessageDto>
    fun checkAndGetMessageById(id:Long): MessageDto
    fun updateMessage(authentication: Authentication?, id:Long, messageDto: MessageDto): MessageDto
    fun sendMessage(authentication: Authentication?, idMessage: Long)
}
