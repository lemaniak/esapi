package com.vicente.validation.impl;

import com.vicente.model.Status;
import com.vicente.validation.decl.ValidStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by vicente on 10/11/16.
 */
public class ValidStatusValidator implements ConstraintValidator<ValidStatus,String> {

    @Override
    public void initialize(ValidStatus validStatus) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value!=null && !value.isEmpty()){
            try {
                Status.fromValue(value);
            }catch (IllegalArgumentException ex){
                return false;
            }
            return true;
        }else{
            return true;
        }
    }
}
