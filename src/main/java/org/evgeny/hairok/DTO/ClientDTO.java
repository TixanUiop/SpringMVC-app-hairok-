package org.evgeny.hairok.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    Long id;

    String name;

    String surname;

    String patronymic;

    LocalDate birthday;

    String phone;

    String city;

}
