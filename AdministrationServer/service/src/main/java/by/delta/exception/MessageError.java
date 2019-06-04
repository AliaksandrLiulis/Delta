package by.delta.exception;

import by.delta.exception.errorCode.ServiceErrorCode;

@SuppressWarnings("serial")
public class MessageError extends ServiceAbstractException {

    public MessageError() {
        //default constructor
    }

    public MessageError(final Throwable e) {
        super(e);
    }

    public MessageError(final ServiceErrorCode errorCode) {
        super(errorCode.getErrorCode());
    }

    public MessageError(final ServiceErrorCode errorCode, final Throwable e) {// NOSONAR
        super(errorCode.getErrorCode(), e);
    }

    public MessageError(final ServiceErrorCode errorCode, final String argument) {
        super(errorCode.getErrorCode());
        this.argument = argument;
    }
}
