package by.delta.service

import by.delta.dto.MessageDto
import org.springframework.security.core.Authentication

interface IMessageService {

    fun createMessage(authentication: Authentication, messageDto: MessageDto): MessageDto
    fun getUserMessages(authentication: Authentication, allRequestParams: MutableMap <String,String>): Set<MessageDto>
}
