package by.delta.converter.impl;

import by.delta.converter.IConverter;
import by.delta.dto.UserDto;
import by.delta.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class UserConverter implements IConverter<User, UserDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User dtoToModel(UserDto userDto) {
        User user = new User();
        modelMapper.map(userDto, user);
        return user;
    }

    @Override
    public UserDto modelToDto(User user) {
        UserDto userDto = new UserDto();
        modelMapper.map(user, userDto);
        return userDto;
    }

    @Override
    public Set<User> dtoToModelList(Set<UserDto> listDto) {
        Set<User> listUser = new LinkedHashSet<>();
        for (UserDto userDto : listDto) {
            listUser.add(dtoToModel(userDto));
        }
        return listUser;
    }

    @Override
    public Set<UserDto> modelToDtoList(Set<User> listModel) {
        Set<UserDto> listUserDto = new LinkedHashSet<>();
        for (User user : listModel) {
            listUserDto.add(modelToDto(user));
        }
        return listUserDto;
    }
}