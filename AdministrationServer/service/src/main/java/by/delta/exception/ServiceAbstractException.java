package by.delta.exception;

@SuppressWarnings("serial")
public abstract class ServiceAbstractException extends RuntimeException {

    protected String argument;// NOSONAR

    public ServiceAbstractException() {
        super();
    }

    public ServiceAbstractException(final Throwable e) {
        super(e);
    }

    public ServiceAbstractException(final String errorCode) {
        super(errorCode);
    }

    public ServiceAbstractException(final String errorCode, final Throwable e) {// NOSONAR
        super(errorCode, e);

    }

    public ServiceAbstractException(final String errorCode, final String argument) {
        super(errorCode);
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
