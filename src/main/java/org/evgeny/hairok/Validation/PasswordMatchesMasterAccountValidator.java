package org.evgeny.hairok.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.evgeny.hairok.DTO.RegisterMasterAccountDTO;
import org.evgeny.hairok.Validation.Annotation.PasswordMatcherMasterAccount;

public class PasswordMatchesMasterAccountValidator implements ConstraintValidator<PasswordMatcherMasterAccount, RegisterMasterAccountDTO> {
    @Override
    public boolean isValid(RegisterMasterAccountDTO registerMasterAccountDTO, ConstraintValidatorContext constraintValidatorContext) {

        if (registerMasterAccountDTO.getPasswordRep() == null || registerMasterAccountDTO.getPassword() == null) {
            return false;
        }

        boolean valid = registerMasterAccountDTO.getPasswordRep().equals(registerMasterAccountDTO.getPassword());

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
