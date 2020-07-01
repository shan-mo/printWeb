package com.paint.util.exception;

public class InvitationException extends RuntimeException {
    private String errCode;

    public InvitationException(String errCode, String message) {
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
