package org.evgeny.hairok.Utill;


import lombok.experimental.UtilityClass;

@UtilityClass
public final class StandardPhoneNumber {

    public String standardizePhone(String input) {
        if (input == null) return null;

        String cleaned = input.trim().replaceAll("[^\\d+]", "");

        if (cleaned.startsWith("+375")) {
            cleaned = "+" + cleaned.substring(1).replaceAll("[^\\d]", "");
        }
        else if (cleaned.startsWith("375")) {
            cleaned = "+" + cleaned;
        }
        else {
            cleaned = "+375" + cleaned;
        }

        cleaned = cleaned.charAt(0) + cleaned.substring(1).replaceAll("\\D", "");

        if (cleaned.length() != 13) {
            throw new IllegalArgumentException("Номер телефона не соответствует формату +375XXXXXXXXX");
        }
        return cleaned;
    }
}
