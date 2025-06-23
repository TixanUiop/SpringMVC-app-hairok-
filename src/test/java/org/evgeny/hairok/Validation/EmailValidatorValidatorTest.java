package org.evgeny.hairok.Validation;

import org.evgeny.hairok.DTO.RegisterMasterAccountDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class EmailValidatorValidatorTest {


    EmailValidatorValidator emailValidatorValidator = new EmailValidatorValidator();

    @Test
    void isValidOnNullDTO() {
        boolean valid = emailValidatorValidator.isValid(getRegisterMasterAccountDTOEmpty(), null);
        Assertions.assertFalse(valid);
    }

    @ParameterizedTest
    @CsvSource({
            "test@inbox.bu, true",
            "1test@inbox.bu, true",
            "testinbox.bu, false",
            "tedsrdsfst@inbox.bu, true",
            "ted2sfst@inbox.bu, true",
            "ted_srdsfst@inbox.bu, true",
            "tedsrds-fst@inbox.bu, true",
    })
    void isValidRegexFunctional(String email, boolean expected) {
        boolean result = emailValidatorValidator.isValid(RegisterMasterAccountDTO.builder().email(email).build(), null );
        Assertions.assertEquals(expected, result);
    }

    private RegisterMasterAccountDTO getRegisterMasterAccountDTOEmpty() {
        return RegisterMasterAccountDTO.builder().build();
    }
}
