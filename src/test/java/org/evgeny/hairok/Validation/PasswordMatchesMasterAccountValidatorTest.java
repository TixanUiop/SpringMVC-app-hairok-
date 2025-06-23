package org.evgeny.hairok.Validation;

import org.evgeny.hairok.DTO.RegisterMasterAccountDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordMatchesMasterAccountValidatorTest {

    PasswordMatchesMasterAccountValidator passwordMatchesMasterAccountValidator = new PasswordMatchesMasterAccountValidator();

    @Test
    void isValidNullPassword() {
        boolean result = passwordMatchesMasterAccountValidator.isValid(getNullRegisterMasterAccountDTO(), null);
        assertFalse(result);
    }

    @Test
    void isValidRepeatPasswordOk() {
        boolean result = passwordMatchesMasterAccountValidator.isValid(getRepPasswordRegisterMasterAccountDTO(), null);
        assertTrue(result);
    }


    private RegisterMasterAccountDTO getRepPasswordRegisterMasterAccountDTO() {
        return RegisterMasterAccountDTO.builder()
                .email("email@email.com")
                .password("password")
                .passwordRep("password")
                .build();
    }

    private RegisterMasterAccountDTO getNullRegisterMasterAccountDTO() {
        return RegisterMasterAccountDTO.builder()
                .email("email@email.com")
                .password(null)
                .passwordRep("password")
                .build();
    }

}