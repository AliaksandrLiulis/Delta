package by.delta.exception;

import by.delta.exception.errorCode.ServiceErrorCode;

@SuppressWarnings("serial")
public class ValidationException extends ServiceAbstractException {

    public ValidationException() {
        //default constructor
    }

    public ValidationException(final Throwable e) {
        super(e);
    }

    public ValidationException(final ServiceErrorCode errorCode) {
        super(errorCode.getErrorCode());
    }

    public ValidationException(final ServiceErrorCode errorCode, final Throwable e) {// NOSONAR
        super(errorCode.getErrorCode(), e);
    }

    public ValidationException(final ServiceErrorCode errorCode, final String argument) {
        super(errorCode.getErrorCode());
        this.argument = argument;
    }
}