package com.vicente.exception;

/**
 * Created by vicente on 09/11/16.
 */
public class EsapiValidationException extends RuntimeException {


    public EsapiValidationException() {
    }

    public EsapiValidationException(String message) {
        super(message);
    }

    public EsapiValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
