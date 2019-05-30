package by.delta.exception;

import by.delta.exception.errorCode.ServiceErrorCode;

@SuppressWarnings("serial")
public class ModelSameServiceException extends ServiceAbstractException {

    public ModelSameServiceException() {
        //default constructor
    }

    public ModelSameServiceException(final Throwable e) {
        super(e);
    }

    public ModelSameServiceException(final ServiceErrorCode errorCode) {
        super(errorCode.getErrorCode());
    }

    public ModelSameServiceException(final ServiceErrorCode errorCode, final Throwable e) {// NOSONAR
        super(errorCode.getErrorCode(), e);
    }

    public ModelSameServiceException(final ServiceErrorCode errorCode, final String argument) {
        super(errorCode.getErrorCode());
        this.argument = argument;
    }
}

