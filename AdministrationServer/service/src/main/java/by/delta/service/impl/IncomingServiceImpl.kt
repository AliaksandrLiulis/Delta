package by.delta.service.impl

import by.delta.dto.IncomingDto
import by.delta.model.Incoming
import by.delta.repository.IRepository
import by.delta.service.IIncomingService
import by.delta.validator.AuthenticationValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
open class IncomingServiceImpl @Autowired constructor(private val incomingRepository: IRepository<Incoming>,
                                                      private val authenticationValidator: AuthenticationValidator
) : IIncomingService {

    override fun createIncoming(authentication: Authentication?, incomingDto: IncomingDto): IncomingDto {

    }
}