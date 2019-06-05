package by.delta.controller

import by.delta.dto.IncomingDto
import by.delta.service.IIncomingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "/messages/recipients", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
class IncomingController @Autowired constructor(private val incomingService: IIncomingService) {

    @PostMapping(consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    fun createIncoming(authentication: Authentication?, @RequestBody resource:MutableMap<String, Array<Long>>): List<IncomingDto> {
       return incomingService.createIncoming(authentication, resource)
    }
}