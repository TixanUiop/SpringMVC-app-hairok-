package org.evgeny.hairok.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.evgeny.hairok.DTO.MasterProfilesDTO;
import org.evgeny.hairok.Validation.Annotation.PhoneRegexPatternMaster;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class PhoneRegexMatcherMasterAccount implements ConstraintValidator<PhoneRegexPatternMaster, MasterProfilesDTO> {

        @Override
        public boolean isValid(MasterProfilesDTO masterProfilesDTO, ConstraintValidatorContext constraintValidatorContext) {
            if (masterProfilesDTO == null) {
                return false;
            }
            Matcher matcher = Pattern.compile("^(\\+375|375)?(44|29|25|33)[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2}$").matcher(masterProfilesDTO.getPhone());
            return matcher.matches();
        }
    }

