package com.azericard.user.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(
        regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$+%^&*-]).{8,}$",   // NOSONAR
        message = "pass is invalid"
)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface Password {

    String message() default
            "should be including at least 1 uppercase letter, 1 lowercase letter, 1 special character, "
                    + "1 number, and a length between 8 and 30 characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
