package by.delta.validator;

import by.delta.exception.errorCode.ServiceErrorCode;
import by.delta.util.ConstParamService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationValidator {
    public void validate(final Authentication authentication) {
        CommonValidator.checkNullObject(authentication, "Authentication reference is null", ServiceErrorCode.REFERENCE_AUTHENTICATION_IS_NULL, ConstParamService.AUTHENTICATION_STRING);
    }
}
