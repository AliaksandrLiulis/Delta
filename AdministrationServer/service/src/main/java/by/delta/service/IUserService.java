package by.delta.service;

import by.delta.dto.UserDto;
import by.delta.model.User;

import java.util.List;

public interface IUserService {

    UserDto createUser(UserDto userDto);
    List<User> getUserByEmail(final String email);
}
