package com.paint.util.exception;

public class UserException extends Exception {
    private String errCode;

    public UserException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
