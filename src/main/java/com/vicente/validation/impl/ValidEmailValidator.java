package com.vicente.validation.impl;

import com.vicente.exception.EsapiException;
import com.vicente.services.decl.AccountService;
import com.vicente.validation.decl.ValidEmail;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by vicente on 29/02/16.
 */
public class ValidEmailValidator implements ConstraintValidator<ValidEmail,String> {

    @Autowired
    private AccountService accountService;

    private boolean emailExistValidate;
    private int errorCode;

    public void initialize(ValidEmail validEmail) {
        this.emailExistValidate=validEmail.emailExistValidate();
        this.errorCode=validEmail.errorCode();
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.isBlank(value) && value.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
            try {
                if(emailExistValidate && accountService.isEmailRegistered(value)){
                    constraintValidatorContext.disableDefaultConstraintViolation();
                    constraintValidatorContext.buildConstraintViolationWithTemplate("esapi.account.email.inuse").addConstraintViolation();
                    return false;
                }else{
                    return true;
                }
            } catch (EsapiException e) {
                return false;
            }
        }else{
            return false;
        }
    }
}
