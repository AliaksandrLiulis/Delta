package by.delta.controller

import by.delta.dto.MessageDto
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
    open fun createMessage(authentication: Authentication?, @RequestBody resource: MessageDto): MessageDto =
            messageService.createMessage(authentication, resource)


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    open fun getUserMessages(authentication: Authentication?, @RequestParam allRequestParams: MutableMap<String, String>): Map<String, Any> {
        return messageService.getUserMessages(authentication, allRequestParams)
    }


    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    open fun getMessageById(authentication: Authentication?, @PathVariable("id") id: Long): MessageDto {
        return messageService.getMessageByIdMessageForUser(authentication, id)
    }


    @PutMapping(value = "/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    open fun updateMessage(authentication: Authentication?, @PathVariable("id") id: Long, @RequestBody resource: MessageDto): MessageDto =
            messageService.updateMessage(authentication, id, resource)


    @PostMapping(value = "/send/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    open fun sendMessage(authentication: Authentication?, @PathVariable("id")id: Long) {
      messageService.sendMessage(authentication, id)
    }

}