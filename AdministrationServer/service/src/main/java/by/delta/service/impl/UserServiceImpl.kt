package by.delta.service.impl

import by.delta.converter.IConverter
import by.delta.dto.FaceDto
import by.delta.dto.UserDto
import by.delta.exception.ModelSameServiceException
import by.delta.exception.errorCode.ServiceErrorCode
import by.delta.model.User
import by.delta.repository.IRepository
import by.delta.service.IUserService
import by.delta.specification.impl.user.GetAllUserByName
import by.delta.specification.impl.user.GetAllUsers
import by.delta.specification.impl.user.GetUserByEmail
import by.delta.util.ConstParamService
import by.delta.util.Helper
import by.delta.validator.UserValidator
import by.delta.validator.paramsvalidator.UserParametersValidator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.CollectionUtils

import java.util.Arrays

@Service(value = "userService")
open class UserServiceImpl @Autowired constructor(private val bCryptPasswordEncoder: BCryptPasswordEncoder,
                                                  private val userConverter: IConverter<User, UserDto>,
                                                  private val userRepository: IRepository<User>,
                                                  private val userValidator: UserValidator,
                                                  private val faceService: FaceServiceImpl,
                                                  private val userParametersValidator: UserParametersValidator
) : UserDetailsService, IUserService {

    override fun loadUserByUsername(str: String): UserDetails {
        val users: List<User>
        if (str.contains(ConstParamService.CHAR_EMAIL_STRING)) {
            users = getUserByEmail(str)
        } else {
            users = getUserByName(str)
        }
        if (CollectionUtils.isEmpty(users)) {
            throw UsernameNotFoundException("Invalid email or password.")
        }
        return org.springframework.security.core.userdetails.User(users[0].email,
                users[0].password, getAuthority(users[0]))
    }

    @Transactional
    override fun createUser(userDto: UserDto): UserDto {
        userValidator.validate(userDto)
        userDto.password = bCryptPasswordEncoder.encode(userDto.password)
        val user = userConverter.dtoToModel(userDto)
        if (userDto.name != null) {
            checkExistUserByName(userDto.name)
        }
        checkExistUserByEmail(user.email)
        val savedUser = userConverter.modelToDto(userRepository.save(user))
        val faceDto = createFaceDtoForSavedUserDto(savedUser.email, savedUser)
        faceService.createFace(faceDto)
        return savedUser
    }

    override fun getAllUsers(allRequestParams: MutableMap<String, String>): Set<UserDto> {
        val newParams = userParametersValidator.validate(allRequestParams)
        return userConverter.modelToDtoList(userRepository.query(GetAllUsers(newParams),
                allRequestParams.getValue(ConstParamService.LIMIT).toInt(),
                allRequestParams.getValue(ConstParamService.OFFSET).toInt()).toSet())
    }

    private fun createFaceDtoForSavedUserDto(email: String, user: UserDto): FaceDto {
        val faceDto = FaceDto()
        faceDto.faceName = email
        faceDto.userDto = user
        return faceDto
    }

    private fun getAuthority(user: User): MutableCollection<out GrantedAuthority>? {
        return Arrays.asList(SimpleGrantedAuthority(user.role.toString()))
    }

    private fun getUserByName(userName: String): List<User> {
        return userRepository.query(GetAllUserByName(Helper.getWraperName(userName)), 1, 0)
    }

    override fun getUserByEmail(email: String): List<User> {
        return userRepository.query(GetUserByEmail(Helper.getWraperEmail(email)), 1, 0)
    }

    private fun checkExistUserByName(name: String) {
        if (!CollectionUtils.isEmpty(getUserByName(name))) {
            LOGGER.error("User with such name exist")
            throw ModelSameServiceException(ServiceErrorCode.NAME_USER_EXISTS, ConstParamService.USER_NAME_STRING)
        }
    }

    private fun checkExistUserByEmail(email: String) {
        if (!CollectionUtils.isEmpty(getUserByEmail(email))) {
            LOGGER.error("User with such e-mail exist")
            throw ModelSameServiceException(ServiceErrorCode.EMAIL_USER_EXISTS, ConstParamService.USER_EMAIL_STRING)
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }
}