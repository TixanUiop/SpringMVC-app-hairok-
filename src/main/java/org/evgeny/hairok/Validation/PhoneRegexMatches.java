package org.evgeny.hairok.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.evgeny.hairok.DTO.RegisterClientDTO;
import org.evgeny.hairok.Validation.Annotation.PhoneRegexPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneRegexMatches implements ConstraintValidator<PhoneRegexPattern, RegisterClientDTO> {

    @Override
    public boolean isValid(RegisterClientDTO registerClientDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (registerClientDTO == null) {
            return false;
        }
        Matcher matcher = Pattern.compile("^(\\+375|375)?(44|29|25|33)[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2}$").matcher(registerClientDTO.getPhone());
        return matcher.matches();
    }
}
