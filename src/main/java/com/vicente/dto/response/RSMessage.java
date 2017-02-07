package com.vicente.dto.response;

/**
 * Created by vicente on 09/11/16.
 */
public class RSMessage {

    private String message;
    private RSMessageType type;
    private int errorCode;

    public RSMessage() {
        super();
    }

    public RSMessage(RSMessageType type, String message,int errorCode) {
        super();
        this.message = message;
        this.type = type;
        this.errorCode=errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RSMessageType getType() {
        return type;
    }

    public void setType(RSMessageType type) {
        this.type = type;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
