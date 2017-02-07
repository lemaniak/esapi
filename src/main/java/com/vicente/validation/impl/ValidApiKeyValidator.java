package com.vicente.validation.impl;


import com.vicente.exception.EsapiException;
import com.vicente.exception.EsapiValidationException;
import com.vicente.model.Account;
import com.vicente.services.decl.AccountService;
import com.vicente.utils.CryptoUtils;
import com.vicente.utils.TokenUtils;
import com.vicente.validation.decl.ValidApiKey;
import com.vicente.validation.decl.ValidToken;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by vicente on 13/11/16.
 */
public class ValidApiKeyValidator implements ConstraintValidator<ValidApiKey, String> {

    @Autowired
    private AccountService accountService;

    public void initialize(ValidApiKey validToken) {
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("esapi.validation.apikey.required").addConstraintViolation();
            return false;
        } else {
            try {
                return accountService.existsApiKey(value);
            } catch (EsapiException e) {
               return false;
            }
        }
    }
}