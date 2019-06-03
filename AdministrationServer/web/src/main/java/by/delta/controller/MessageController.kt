package by.delta.controller

import by.delta.dto.MessageDto
import by.delta.dto.UserDto
import by.delta.service.IMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "/messages", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
open class MessageController @Autowired constructor(private val messageService: IMessageService) {

    @PostMapping(consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    fun createMessage(authentication: Authentication, @RequestBody resource: MessageDto): MessageDto =
            messageService.createMessage(authentication, resource)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getUserMessages(authentication: Authentication, @RequestParam allRequestParams: MutableMap <String, String>):Map<String, Any> {
        return messageService.getUserMessages(authentication, allRequestParams)
    }

}