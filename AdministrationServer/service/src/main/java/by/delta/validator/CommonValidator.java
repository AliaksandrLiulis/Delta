package by.delta.validator;

import by.delta.exception.ValidationException;
import by.delta.exception.errorCode.ServiceErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private CommonValidator() {
        //default constructor
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonValidator.class);

    public static void checkNullObject(Object dto, String loggerValue, ServiceErrorCode errorMessage, String stringValue) {
        if (dto == null) {
            LOGGER.error(loggerValue);
            throw new ValidationException(errorMessage, stringValue);
        }
    }

    public static void checkIdNullParams(Long idParameter, String loggerValue, ServiceErrorCode errorMessage, String stringValue) {
        if (idParameter == null) {
            LOGGER.error(loggerValue);
            throw new ValidationException(errorMessage, stringValue);
        }
    }

    public static void checkIdLengthParams(Long idParameter, int lengthValue, String loggerValue, ServiceErrorCode errorMessage, String stringValue) {
        if (idParameter.toString().length() > lengthValue) {
            LOGGER.error(loggerValue);
            throw new ValidationException(errorMessage, stringValue);
        }
    }

    public static void checkIdLessParams(Long idParameter, int checkParams, String loggerValue, ServiceErrorCode errorMessage, String stringValue) {
        if (idParameter != null) {
            if (idParameter < checkParams) {
                LOGGER.error(loggerValue);
                throw new ValidationException(errorMessage, stringValue);
            }
        }
    }

    public static void checkIdEqualsParams(Long idParameter, int checkParams, String loggerValue, ServiceErrorCode errorMessage, String stringValue) {
        if (idParameter != null) {
            if (idParameter == checkParams) {
                LOGGER.error(loggerValue);
                throw new ValidationException(errorMessage, stringValue);
            }
        }
    }

    public static void checkNullStringParams(String checkParameter, String loggerValue, ServiceErrorCode errorMessage, String stringValue) {
        if (checkParameter == null) {
            LOGGER.error(loggerValue);
            throw new ValidationException(errorMessage, stringValue);
        }
    }

    public static void checkEmptyStringParams(String checkParameter, String loggerValue, ServiceErrorCode errorMessage, String stringValue) {
        if (checkParameter != null) {
            if (checkParameter.trim().isEmpty()) {
                LOGGER.error(loggerValue);
                throw new ValidationException(errorMessage, stringValue);
            }
        }
    }

    public static void checkLengthStringParams(String checkParameter, String loggerValue, int lengthValue, ServiceErrorCode errorMessage, String stringValue) {
        if (checkParameter != null) {
            if (checkParameter.trim().length() > lengthValue) {
                LOGGER.error(loggerValue);
                throw new ValidationException(errorMessage, stringValue);
            }
        }
    }

    public static void checkEmail(String checkEmail, String loggerValue, ServiceErrorCode errorMessage, String stringValue) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(checkEmail);
        if (!matcher.find()) {
            LOGGER.error(loggerValue);
            throw new ValidationException(errorMessage, stringValue);
        }
    }

    public static void checkNumberParams(String numberParams, String loggerValue, ServiceErrorCode errorMessage, String stringValue) {
        try {
            Long.parseLong(numberParams);
        } catch (NumberFormatException e) {
            LOGGER.error(loggerValue);
            throw new ValidationException(errorMessage, stringValue);
        }
    }

    public static void checkSexParams(String sexParams, String loggerValue, ServiceErrorCode errorMessage, String stringValue) {
        if (sexParams != null) {
            if (!sexParams.equalsIgnoreCase("M") && !sexParams.equalsIgnoreCase("W")) {
                LOGGER.error(loggerValue);
                throw new ValidationException(errorMessage, stringValue);
            }
        }
    }
}