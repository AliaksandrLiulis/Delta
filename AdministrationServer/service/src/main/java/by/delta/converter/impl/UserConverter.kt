package by.delta.converter.impl

import by.delta.converter.IConverter
import by.delta.dto.UserDto
import by.delta.model.User
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util.LinkedHashSet

@Component
open class UserConverter @Autowired constructor(private val modelMapper: ModelMapper)
    : IConverter<User, UserDto> {

    override fun dtoToModel(userDto: UserDto): User {
        val user = User()
        modelMapper!!.map(userDto, user)
        return user
    }

    override fun modelToDto(user: User): UserDto {
        val userDto = UserDto()
        modelMapper!!.map(user, userDto)
        return userDto
    }

    override fun dtoToModelList(listDto: Set<UserDto>): Set<User> {
        val listUser = LinkedHashSet<User>()
        for (userDto in listDto) {
            listUser.add(dtoToModel(userDto))
        }
        return listUser
    }

    override fun modelToDtoList(listModel: Set<User>): Set<UserDto> {
        val listUserDto = LinkedHashSet<UserDto>()
        for (user in listModel) {
            listUserDto.add(modelToDto(user))
        }
        return listUserDto
    }
}