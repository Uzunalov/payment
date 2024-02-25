package com.azericard.user.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(
        regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
        message = "email is invalid"
)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface Email {

    String message() default "should be in the format example@azericard.com";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
