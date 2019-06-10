package by.delta.controller

import by.delta.dto.UserDto
import by.delta.model.Role
import by.delta.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "/users", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))

open class UserController @Autowired constructor(private val userService: IUserService) {

    @PostMapping(value = "/signUp", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    open fun createUser(@RequestBody resource: UserDto): UserDto {
        resource.role = Role.ROLE_USER
        return userService.createUser(resource)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    open fun getAllUsers(@RequestParam allRequestParams: MutableMap <String, String>): Map<String, Any> {
        return userService.getAllUsers(allRequestParams)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    open fun updateUsers(authentication: Authentication, @RequestBody resource: UserDto): UserDto{
        return userService.updateUser(authentication, resource)
    }
}