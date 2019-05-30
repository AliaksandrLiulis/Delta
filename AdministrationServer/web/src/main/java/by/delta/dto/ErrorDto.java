package by.delta.dto;

public class ErrorDto {

    private String errorCode;
    private String message;

    public ErrorDto() {
        //default constructor
    }

    public ErrorDto(String message, String errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}