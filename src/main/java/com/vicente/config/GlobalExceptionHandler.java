package com.vicente.config;

import com.vicente.dto.response.RSMessage;
import com.vicente.dto.response.RSMessageType;
import com.vicente.exception.EsapiException;
import com.vicente.exception.EsapiValidationException;
import com.vicente.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by vicente on 09/11/16.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private MessageSource msgSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RSMessage processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        return processError(error.getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RSMessage processValidationError(HttpMessageNotReadableException ex) {
        return processError("esapi.validation.invalid.request");
    }

    @ExceptionHandler(value = EsapiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RSMessage handleException(EsapiException e){
        if(e.getMessage().contains("esapi")){
            return processError(e.getMessage());
        }else{
            return processError("esapi.validation.internal.error");
        }

    }

    @ExceptionHandler(value = EsapiValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RSMessage handleException(EsapiValidationException e){
        return processError(e.getMessage());
    }


    private RSMessage processError(String error) {
        RSMessage message = null;
        if (error != null) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String msg = msgSource.getMessage(error, null, currentLocale);

            message = Utils.errorResponse(msg);
        }
        return message;
    }


}
