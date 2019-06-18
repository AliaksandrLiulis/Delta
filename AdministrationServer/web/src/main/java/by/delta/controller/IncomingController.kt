package by.delta.controller


import by.delta.dto.IncomingDto
import by.delta.dto.MessageDto
import by.delta.service.IIncomingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "/messages/incomings", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
open class IncomingController @Autowired constructor(private val incomingService: IIncomingService) {


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getUserIncoming(authentication: Authentication?, @RequestParam allRequestParams: MutableMap <String, String>): Map<String, Any> {
        return incomingService.getUserIncoming(authentication, allRequestParams)
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserIncomingByIdMessage(authentication: Authentication?, @PathVariable("id") id: Long): MessageDto {
        return incomingService.getIncomingByMessageId(authentication, id)
    }
}