package org.evgeny.hairok.Validation.Annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.evgeny.hairok.Validation.PhoneRegexMatches;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneRegexMatches.class)
public @interface PhoneRegexPattern {

    String message() default "Номер телефона не соответствует шаблону";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
