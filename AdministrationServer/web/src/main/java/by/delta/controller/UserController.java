package by.delta.controller;

import by.delta.dto.UserDto;
import by.delta.model.Role;
import by.delta.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto createUser(@RequestBody final UserDto resource) {
        resource.setRole(Role.ROLE_USER);
        return userService.createUser(resource);
    }
}