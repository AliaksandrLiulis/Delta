package by.delta.service

import by.delta.dto.IncomingDto
import org.springframework.security.core.Authentication

interface IIncomingService {

    fun createIncoming(authentication: Authentication?,resource:MutableMap<String, Any>): List<IncomingDto>
}