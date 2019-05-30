package by.delta.exception;

import by.delta.dto.ErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private ErrorDto validationMessage(String errorCode, String argument) {
        String errorMessage = messageSource.getMessage(
                errorCode + ".MESSAGE", new Object[]{argument}, LocaleContextHolder.getLocale());
        return new ErrorDto(errorMessage, errorCode);
    }

    @ExceptionHandler(ModelSameServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleModelSameServiceException(ModelSameServiceException e) {
        return validationMessage(e.getMessage(), e.getArgument());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationException(ValidationException e) {
        return validationMessage(e.getMessage(), e.getArgument());
    }
}