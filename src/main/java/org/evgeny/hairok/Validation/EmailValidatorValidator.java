package org.evgeny.hairok.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.evgeny.hairok.DTO.RegisterMasterAccountDTO;
import org.evgeny.hairok.Validation.Annotation.EmailMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorValidator implements ConstraintValidator<EmailMatcher, RegisterMasterAccountDTO> {

    @Override
    public boolean isValid(RegisterMasterAccountDTO registerMasterAccountDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (registerMasterAccountDTO.getEmail() == null) {
            return false;
        }
        Matcher matcher = Pattern.compile("^[A-Za-z]+@[A-Za-z]{2,}\\.[A-Za-z]{2,10}$").matcher(registerMasterAccountDTO.getEmail());
        return matcher.matches();
    }
}
