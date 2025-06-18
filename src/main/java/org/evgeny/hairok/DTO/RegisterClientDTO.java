package org.evgeny.hairok.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.evgeny.hairok.Validation.Annotation.PasswordMatches;
import org.evgeny.hairok.Validation.Annotation.PhoneRegexPattern;

import java.time.LocalDate;

@Data
@Builder
@PasswordMatches
@PhoneRegexPattern
public class RegisterClientDTO {

    @NotBlank(message = "Имя не может быть пустым")
    String name;

    @NotBlank(message = "Фамилия не может быть пустой")
    String surname;

    @NotBlank(message = "Отчество не может быть пустым")
    String patronymic;

    @Past(message = "Укажите дату корректно")
    @NotNull(message = "Дата не может быть пустой")
    LocalDate birthday;

    @NotBlank(message = "Телефон не может быть пустым")
    String phone;

    @NotBlank(message = "Город не может быть пустым")
    String city;

    @NotBlank(message = "Пароль не может быть пустым")
    String password;

    @NotBlank(message = "Повторите пароль")
    String passwordRep;

}
