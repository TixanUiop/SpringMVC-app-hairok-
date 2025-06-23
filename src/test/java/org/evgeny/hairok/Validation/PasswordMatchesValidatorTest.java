package org.evgeny.hairok.Validation;

import org.evgeny.hairok.DTO.RegisterClientDTO;
import org.evgeny.hairok.DTO.RegisterMasterAccountDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PasswordMatchesValidatorTest {

    PasswordMatchesValidator passwordMatchesValidator = new PasswordMatchesValidator();

    @Test
    void isValidNullPassword() {
        boolean result = passwordMatchesValidator.isValid(getNullRegisterClientDTOCorrect(), null);
        assertFalse(result);
    }

    @Test
    void isValidRepeatPasswordOk() {
        boolean result = passwordMatchesValidator.isValid(getRegisterClientDTOCorrect(), null);
        assertTrue(result);
    }


    private RegisterClientDTO getRegisterClientDTOCorrect() {
        return RegisterClientDTO.builder()
                .password("password")
                .passwordRep("password")
                .birthday(LocalDate.now())
                .city("city")
                .name("name")
                .phone("+375292327431")
                .surname("surname")
                .patronymic("patronymic")
                .build();
    }

    private RegisterClientDTO getNullRegisterClientDTOCorrect() {
        return RegisterClientDTO.builder()
                .password("password")
                .passwordRep(null)
                .birthday(LocalDate.now())
                .city("city")
                .name("name")
                .phone("+375292327431")
                .surname("surname")
                .patronymic("patronymic")
                .build();
    }

}