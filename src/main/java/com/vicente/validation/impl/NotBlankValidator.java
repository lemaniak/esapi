package com.vicente.validation.impl;

import com.vicente.exception.EsapiException;
import com.vicente.validation.decl.NotBlank;
import com.vicente.validation.decl.ValidEmail;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by vicente on 29/02/16.
 */
public class NotBlankValidator implements ConstraintValidator<NotBlank,String> {



    private int errorCode;

    public void initialize(NotBlank notBlank) {
        this.errorCode=notBlank.errorCode();
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.isBlank(value)){
           return true;
        }else{
            return false;
        }
    }
}
