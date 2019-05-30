package by.delta.service.impl;

import by.delta.converter.IConverter;
import by.delta.dto.FaceDto;
import by.delta.dto.UserDto;
import by.delta.exception.ModelSameServiceException;
import by.delta.exception.errorCode.ServiceErrorCode;
import by.delta.model.User;
import by.delta.repository.IRepository;
import by.delta.service.IUserService;
import by.delta.specification.impl.user.GetAllUserByName;
import by.delta.specification.impl.user.GetUserByEmail;
import by.delta.util.ConstParamService;
import by.delta.util.Helper;
import by.delta.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IConverter<User, UserDto> userConverter;
    @Autowired
    private IRepository<User> userRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private FaceServiceImpl faceService;

    @Override
    public UserDetails loadUserByUsername(String str) {
        List<User> users;
        if (str.contains(ConstParamService.CHAR_EMAIL_STRING)) {
            users = getUserByEmail(str);
        } else {
            users = getUserByName(str);
        }
        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(users.get(0).getEmail(),
                users.get(0).getPassword(), getAuthority(users.get(0)));
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        userValidator.validate(userDto);
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User user = userConverter.dtoToModel(userDto);
        if (userDto.getName() != null) {
            checkExistUserByName(userDto.getName());
        }
        checkExistUserByEmail(user.getEmail());
        UserDto savedUser = userConverter.modelToDto(userRepository.save(user));
        FaceDto faceDto = createFaceDtoForSavedUserDto(savedUser.getEmail(), savedUser);
        faceService.createFace(faceDto);
        return savedUser;
    }

    private FaceDto createFaceDtoForSavedUserDto(String email, UserDto user) {
        FaceDto faceDto = new FaceDto();
        faceDto.setFaceName(email);
        faceDto.setUserDto(user);
        return faceDto;
    }

    private List getAuthority(User user) {
        return Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    private List<User> getUserByName(final String userName) {
        return userRepository.query(new GetAllUserByName(Helper.getWraperName(userName)), 1, 0);
    }

    public List<User> getUserByEmail(final String email) {
        return userRepository.query(new GetUserByEmail(Helper.getWraperEmail(email)), 1, 0);
    }

    private void checkExistUserByName(final String name) {
        if (!CollectionUtils.isEmpty(getUserByName(name))) {
            LOGGER.error("User with such name exist");
            throw new ModelSameServiceException(ServiceErrorCode.NAME_USER_EXISTS, ConstParamService.USER_NAME_STRING);
        }
    }

    private void checkExistUserByEmail(final String email) {
        if (!CollectionUtils.isEmpty(getUserByEmail(email))) {
            LOGGER.error("User with such e-mail exist");
            throw new ModelSameServiceException(ServiceErrorCode.EMAIL_USER_EXISTS, ConstParamService.USER_EMAIL_STRING);
        }
    }
}