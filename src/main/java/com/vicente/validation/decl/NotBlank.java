package com.vicente.validation.decl;



import com.vicente.validation.impl.NotBlankValidator;
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
@Constraint(validatedBy = NotBlankValidator.class)
@Documented
public @interface NotBlank {
    String message() default "esapi.default.notblank";

    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};

    int errorCode() default 999;

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotBlank[] value();
    }
}
