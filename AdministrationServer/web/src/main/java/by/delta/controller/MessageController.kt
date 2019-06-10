package by.delta.controller

import by.delta.dto.MessageDto
import by.delta.dto.UserDto
import by.delta.service.IMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "/messages", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
open class MessageController @Autowired constructor(private val messageService: IMessageService) {

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PostMapping(consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    open fun createMessage(authentication: Authentication?, @RequestBody resource: MessageDto): MessageDto =
            messageService.createMessage(authentication, resource)

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getUserMessages(authentication: Authentication?, @RequestParam allRequestParams: MutableMap<String, String>): Map<String, Any> {
        return messageService.getUserMessages(authentication, allRequestParams)
    }

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getMessageById(authentication: Authentication?, @PathVariable("id") id: Long): Set<MessageDto> {
        return messageService.getMessageById(authentication, id)
    }

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PutMapping(value = "/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    open fun updateMessage(authentication: Authentication?, @PathVariable("id") id: Long, @RequestBody resource: MessageDto): MessageDto =
            messageService.updateMessage(authentication, id, resource)

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PostMapping(value = "/send/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    open fun sendMessage(authentication: Authentication?, @PathVariable("id")id: Long) {
      messageService.sendMessage(authentication, id)
    }

}