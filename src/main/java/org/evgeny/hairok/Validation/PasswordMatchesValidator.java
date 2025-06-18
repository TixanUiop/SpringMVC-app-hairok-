package org.evgeny.hairok.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.evgeny.hairok.DTO.RegisterClientDTO;
import org.evgeny.hairok.Validation.Annotation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterClientDTO> {
    @Override
    public boolean isValid(RegisterClientDTO registerClientDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (registerClientDTO.getPasswordRep() == null || registerClientDTO.getPassword() == null) {
            return false;
        }

        boolean valid = registerClientDTO.getPasswordRep().equals(registerClientDTO.getPassword());

        if (!valid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Пароли не совпадают")
                    .addPropertyNode("passwordRep")
                    .addConstraintViolation();
        }
        return valid;
    }
}
