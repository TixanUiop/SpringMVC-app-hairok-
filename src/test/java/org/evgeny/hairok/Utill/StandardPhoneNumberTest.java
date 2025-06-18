package org.evgeny.hairok.Utill;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StandardPhoneNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {"+375 29 292 7452", "29 292 7452", "375 29 292 7452",
            "+375 29-292-7452", "+375 29 292 7452" })
    void standardizePhoneOnSuccessful(String phoneNumber) {
        String expected = "+375292927452";
        String result = StandardPhoneNumber.standardizePhone(phoneNumber);
        assertEquals(expected, result);
    }
}