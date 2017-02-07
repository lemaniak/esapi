package com.vicente.validation.impl;


import com.vicente.exception.EsapiValidationException;
import com.vicente.utils.CryptoUtils;
import com.vicente.utils.TokenUtils;
import com.vicente.validation.decl.ValidToken;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by vicente on 13/11/16.
 */
public class ValidTokenValidator implements ConstraintValidator<ValidToken, String> {

    @Autowired
    private CryptoUtils cryptoUtils;
    @Autowired
    private TokenUtils tokenUtils;

    public void initialize(ValidToken validToken) {
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("esapi.validation.token.required").addConstraintViolation();
            return false;
        } else {
            try {
                String decryptedToken = cryptoUtils.decrypt(value);
                Boolean expired = tokenUtils.isExpiredToken(decryptedToken);
                if (!expired) {
                    return true;
                } else {
                    throw new EsapiValidationException("esapi.error.invalid.token");
                }
            } catch (Exception ex) {
                throw new EsapiValidationException("esapi.error.invalid.token");
            }
        }
    }
}