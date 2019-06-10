package by.delta.service

import by.delta.dto.IncomingDto
import by.delta.model.Incoming
import org.springframework.security.core.Authentication

interface IIncomingService {

    fun createIncoming(authentication: Authentication?, resource: MutableMap<String, Any>): List<IncomingDto>

    fun getUserIncoming(authentication: Authentication?, allRequestParams: MutableMap<String, String>): Map<String, Any>

    fun getIncomingByMessageId(messageId:Long): List<Incoming>

    fun updateIncoming(incoming:Incoming):Incoming
}