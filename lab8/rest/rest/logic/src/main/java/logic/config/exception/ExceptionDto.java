package logic.config.exception;

public class ExceptionDto {

    private String errorCode;
    private int hhtpStatus;
    private String message;

    public ExceptionDto(String errorCode, int hhtpStatus, String message) {
        this.errorCode = errorCode;
        this.hhtpStatus = hhtpStatus;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public int getHhtpStatus() {
        return hhtpStatus;
    }

    public void setHhtpStatus(int hhtpStatus) {
        this.hhtpStatus = hhtpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

