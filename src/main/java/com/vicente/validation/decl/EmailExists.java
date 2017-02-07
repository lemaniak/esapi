package com.vicente.validation.decl;



import com.vicente.validation.impl.EmailExistsValidator;
import com.vicente.validation.impl.ValidEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by vicente on 29/02/16.
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailExistsValidator.class)
@Documented
public @interface EmailExists {

    String message() default "esapi.account.email.does.not.exists";

    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};


    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        EmailExists[] value();
    }
}
