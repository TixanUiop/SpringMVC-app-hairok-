package org.evgeny.hairok.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.evgeny.hairok.Validation.Annotation.PhoneRegexPatternMaster;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PhoneRegexPatternMaster
public class MasterProfilesDTO {

    MultipartFile avatar;

    @NotBlank(message = "Имя не может быть пустым")
    String name;

    @NotBlank(message = "Фамилия не может быть пустой")
    String surname;

    @NotBlank(message = "Отчество не может быть пустым")
    String patronymic;

    @NotNull(message = "Выберите дату")
    @Past(message = "Дата должна быть в прошлом")
    LocalDate birthday;

    @NotBlank(message = "Телефон не может быть пустым")
    String phone;

    @NotBlank(message = "Город не может быть пустым")
    String city;


}
