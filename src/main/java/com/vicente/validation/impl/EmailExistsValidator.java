package com.vicente.validation.impl;

import com.vicente.services.decl.AccountService;
import com.vicente.validation.decl.EmailExists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by vicente on 29/02/16.
 */
public class EmailExistsValidator implements ConstraintValidator<EmailExists,String> {

    @Autowired
    private AccountService accountService;


    public void initialize(EmailExists emailExists) {
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if(!StringUtils.isBlank(value) && accountService.isEmailRegistered(value)){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            return false;
        }
    }
}
