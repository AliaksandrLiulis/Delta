package by.delta.validator;

import by.delta.dto.UserDto;
import by.delta.exception.errorCode.ServiceErrorCode;
import by.delta.util.ConstParamService;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validate(final UserDto userDto) {
        CommonValidator.checkNullObject(userDto, "User reference is null", ServiceErrorCode.REFERENCE_USER_IS_NULL, ConstParamService.USER_STRING);
        if (userDto.getEmail() != null && userDto.getPassword() != null) {
            userDto.setEmail(userDto.getEmail().trim());
            userDto.setPassword(userDto.getPassword().trim());
        }

        if (userDto.getNickName() != null) {
            userDto.setNickName(userDto.getNickName().trim());
            validateNickName(userDto);
        }

        if (userDto.getName() != null) {
            userDto.setName(userDto.getName().trim());
            validateName(userDto);
        }

        if (userDto.getSurName() != null) {
            userDto.setSurName(userDto.getSurName().trim());
            validateSurName(userDto);
        }

        if (userDto.getPatronymic() != null) {
            userDto.setPatronymic(userDto.getPatronymic().trim());
            validatePatronymic(userDto);
        }

        if (userDto.getSex() != null) {
            userDto.setSex(userDto.getSex().trim());
            validateSex(userDto);
        }

        if (userDto.getPassword() != null) {
            userDto.setPassword(userDto.getPassword().trim());
        }

        validateEmail(userDto);
        validatePassword(userDto);
    }

    public void validateUserForUpdate(final UserDto userDto) {
        CommonValidator.checkNullObject(userDto, "User reference is null", ServiceErrorCode.REFERENCE_USER_IS_NULL, ConstParamService.USER_STRING);

        if (userDto.getNickName() != null) {
            userDto.setNickName(userDto.getNickName().trim());
            validateNickName(userDto);
        }

        if (userDto.getName() != null) {
            userDto.setName(userDto.getName().trim());
            validateName(userDto);
        }

        if (userDto.getSurName() != null) {
            userDto.setSurName(userDto.getSurName().trim());
            validateSurName(userDto);
        }

        if (userDto.getPatronymic() != null) {
            userDto.setPatronymic(userDto.getPatronymic().trim());
            validatePatronymic(userDto);
        }

        if (userDto.getSex() != null) {
            userDto.setSex(userDto.getSex().trim());
            validateSex(userDto);
        }

        if (userDto.getPassword() != null) {
            userDto.setPassword(userDto.getPassword().trim());
        }
    }

    public void checkId(final Long id) {
        CommonValidator.checkIdNullParams(id, "User id is null", ServiceErrorCode.ID_USER_IS_NULL, ConstParamService.USER_ID_STRING);
        CommonValidator.checkIdLengthParams(id, 10, "User Id is long so much", ServiceErrorCode.ID_USER_IS_LONG_SO_MUCH, ConstParamService.USER_ID_STRING);
        CommonValidator.checkIdLessParams(id, 0, "User id is less than 0", ServiceErrorCode.ID_USER_IS_LESS_THAN_0, ConstParamService.USER_ID_STRING);
        CommonValidator.checkIdEqualsParams(id, 0, "User id equals 0", ServiceErrorCode.ID_USER_IS_EQUALS_0, ConstParamService.USER_ID_STRING);
    }

    private void validateNickName(final UserDto userDto) {
        CommonValidator.checkNullStringParams(userDto.getNickName(), "User nick name is null", ServiceErrorCode.NICK_NAME_USER_IS_NULL, ConstParamService.USER_NICK_NAME_STRING);
        CommonValidator.checkEmptyStringParams(userDto.getNickName(), "User nick name is Empty", ServiceErrorCode.NICK_NAME_USER_IS_EMPTY, ConstParamService.USER_NICK_NAME_STRING);
        CommonValidator.checkLengthStringParams(userDto.getNickName(), "User nick name is long so much", 30, ServiceErrorCode.NICK_NAME_USER_IS_LONG_SO_MUCH, ConstParamService.USER_NICK_NAME_STRING);
    }

    private void validateName(final UserDto userDto) {
        CommonValidator.checkNullStringParams(userDto.getName(), "User Name is null", ServiceErrorCode.NAME_USER_IS_NULL, ConstParamService.USER_NAME_STRING);
        CommonValidator.checkEmptyStringParams(userDto.getName(), "User Name is Empty", ServiceErrorCode.NAME_USER_IS_EMPTY, ConstParamService.USER_NAME_STRING);
        CommonValidator.checkLengthStringParams(userDto.getName(), "User Name is long so much", 30, ServiceErrorCode.NAME_USER_IS_LONG_SO_MUCH, ConstParamService.USER_NAME_STRING);
    }

    private void validateSurName(final UserDto userDto) {
        CommonValidator.checkNullStringParams(userDto.getSurName(), "User surname is null", ServiceErrorCode.SUR_NAME_USER_IS_NULL, ConstParamService.USER_SURNAME_STRING);
        CommonValidator.checkEmptyStringParams(userDto.getSurName(), "User surname is Empty", ServiceErrorCode.SUR_NAME_USER_IS_EMPTY, ConstParamService.USER_SURNAME_STRING);
        CommonValidator.checkLengthStringParams(userDto.getSurName(), "User surname is long so much", 50, ServiceErrorCode.SUR_NAME_USER_IS_LONG_SO_MUCH, ConstParamService.USER_SURNAME_STRING);
    }

    private void validatePatronymic(final UserDto userDto) {
        CommonValidator.checkNullStringParams(userDto.getPatronymic(), "User patronymic is null", ServiceErrorCode.PATRONYMIC_USER_IS_NULL, ConstParamService.USER_PATRONYMIC_STRING);
        CommonValidator.checkEmptyStringParams(userDto.getPatronymic(), "User patronymic is Empty", ServiceErrorCode.PATRONYMIC_USER_IS_EMPTY, ConstParamService.USER_PATRONYMIC_STRING);
        CommonValidator.checkLengthStringParams(userDto.getPatronymic(), "User patronymic is long so much", 30, ServiceErrorCode.PATRONYMIC_USER_IS_LONG_SO_MUCH, ConstParamService.USER_PATRONYMIC_STRING);
    }

    private void validateSex(final UserDto userDto) {
        CommonValidator.checkNullStringParams(userDto.getSex(), "User sex is null", ServiceErrorCode.SEX_USER_IS_NULL, ConstParamService.USER_SEX_STRING);
        CommonValidator.checkEmptyStringParams(userDto.getSex(), "User sex is Empty", ServiceErrorCode.SEX_USER_IS_EMPTY, ConstParamService.USER_SEX_STRING);
        CommonValidator.checkSexParams(userDto.getSex(), "User sex is not valid", ServiceErrorCode.SEX_USER_IS_NOT_CORRECT, ConstParamService.USER_SEX_STRING);
    }

    private void validatePassword(final UserDto userDto) {
        CommonValidator.checkNullStringParams(userDto.getPassword(), "User Password is null", ServiceErrorCode.PASSWORD_USER_IS_NULL, ConstParamService.USER_PASSWORD_STRING);
        CommonValidator.checkEmptyStringParams(userDto.getPassword(), "User Password is Empty", ServiceErrorCode.PASSWORD_USER_IS_EMPTY, ConstParamService.USER_PASSWORD_STRING);
        CommonValidator.checkLengthStringParams(userDto.getPassword(), "User Password is long so much", 30, ServiceErrorCode.PASSWORD_USER_IS_LONG_SO_MUCH, ConstParamService.USER_PASSWORD_STRING);
    }

    private void validateEmail(final UserDto userDto) {
        CommonValidator.checkNullStringParams(userDto.getEmail(), "User Email is null", ServiceErrorCode.EMAIL_USER_IS_NULL, ConstParamService.USER_EMAIL_STRING);
        CommonValidator.checkEmptyStringParams(userDto.getEmail(), "User Email is Empty", ServiceErrorCode.EMAIL_USER_IS_EMPTY, ConstParamService.USER_EMAIL_STRING);
        CommonValidator.checkLengthStringParams(userDto.getEmail(), "User Email is long so much", 50, ServiceErrorCode.EMAIL_USER_IS_LONG_SO_MUCH, ConstParamService.USER_EMAIL_STRING);
        CommonValidator.checkEmail(userDto.getEmail(), "User Email is not correct", ServiceErrorCode.EMAIL_USER_IS_NOT_CORRECT, ConstParamService.USER_EMAIL_STRING);
    }
}