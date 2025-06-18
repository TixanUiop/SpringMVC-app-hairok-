package org.evgeny.hairok.Mapper;

import org.evgeny.hairok.DTO.ClientDTO;
import org.evgeny.hairok.Entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientToClientDTOMapper implements Mapper<Client, ClientDTO> {
    @Override
    public ClientDTO map(Client client) {
        return ClientDTO.builder()
                .phone(client.getPhone())
                .city(client.getCity())
                .name(client.getName())
                .surname(client.getSurname())
                .patronymic(client.getPatronymic())
                .birthday(client.getBirthday())
                .id(client.getId())
                .build();
    }
}
