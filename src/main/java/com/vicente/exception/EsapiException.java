package com.vicente.exception;

/**
 * Created by vicente on 09/11/16.
 */
public class EsapiException extends Exception {
    private Throwable cause;

    public EsapiException() {
    }

    public EsapiException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
