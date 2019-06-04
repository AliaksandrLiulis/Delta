package by.delta.service

import by.delta.dto.UserDto
import by.delta.model.User
import org.springframework.security.core.Authentication

interface IUserService {

    fun createUser(userDto: UserDto): UserDto
    fun getUserByEmail(email: String): List<User>
    fun getAllUsers(allRequestParams: MutableMap <String, String>): Map<String, Any>
    fun updateUser(authentication: Authentication, userDto: UserDto): UserDto
    fun checkAndGetUserByEmail(email: String): UserDto
    fun getUserById(idUser: Long):UserDto
}
