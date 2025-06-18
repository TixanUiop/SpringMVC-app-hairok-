package org.evgeny.hairok.Mapper;

import org.evgeny.hairok.DTO.RegisterClientDTO;
import org.evgeny.hairok.Entity.Client;
import org.springframework.stereotype.Component;


@Component
public class RegisterMapperDTOToEntity implements Mapper<RegisterClientDTO, Client>{
    @Override
    public Client map(RegisterClientDTO registerClientDTO) {
        return Client.builder()
                .name(registerClientDTO.getName())
                .city(registerClientDTO.getCity())
                .phone(registerClientDTO.getPhone())
                .birthday(registerClientDTO.getBirthday())
                .surname(registerClientDTO.getSurname())
                .patronymic(registerClientDTO.getPatronymic())
                .password(registerClientDTO.getPassword())
                .build();
    }
}
