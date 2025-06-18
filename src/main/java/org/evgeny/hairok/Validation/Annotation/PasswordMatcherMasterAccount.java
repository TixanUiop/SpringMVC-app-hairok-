package org.evgeny.hairok.Validation.Annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.evgeny.hairok.Validation.PasswordMatchesMasterAccountValidator;
import org.evgeny.hairok.Validation.PasswordMatchesValidator;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordMatchesMasterAccountValidator.class)
public @interface PasswordMatcherMasterAccount {
    String message() default "Пароли не совпадают";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
