package by.delta.service

import by.delta.dto.IncomingDto
import by.delta.dto.MessageDto
import by.delta.model.Incoming
import org.springframework.security.core.Authentication

interface IIncomingService {

    fun createIncoming(resource: MutableMap<String, Any>): List<IncomingDto>

    fun getUserIncoming(authentication: Authentication?, allRequestParams: MutableMap<String, String>): Map<String, Any>

    fun getIncomingById(messageId:Long): List<Incoming>

    fun getIncomingByMessageId(authentication: Authentication?,messageId:Long): MessageDto

    fun updateIncoming(incoming:Incoming):Incoming
}