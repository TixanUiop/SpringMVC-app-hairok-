package org.evgeny.hairok.Validation.Annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.evgeny.hairok.Validation.EmailValidatorValidator;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = EmailValidatorValidator.class)
public @interface EmailMatcher {
    String message() default "Пароли не совпадают";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
