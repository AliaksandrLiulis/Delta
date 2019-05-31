package by.delta.service

import by.delta.dto.UserDto
import by.delta.model.User

interface IUserService {

    fun createUser(userDto: UserDto): UserDto
    fun getUserByEmail(email: String): List<User>
    fun getAllUsers(allRequestParams: MutableMap <String, String>):Set<UserDto>
}
