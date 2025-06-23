package org.evgeny.hairok.Service;

import org.evgeny.hairok.DTO.RegisterClientDTO;
import org.evgeny.hairok.Entity.Client;
import org.evgeny.hairok.Repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void loadUserByUsernameExistUserWhenPhoneIsCorrectAndExist() {
        //Given
        String phone = "+375292327431";
        Client clientCorrect = getClientCorrect();
        Mockito.when(clientRepository.findByPhone(phone)).thenReturn(Optional.of(clientCorrect));

        //When
        UserDetails userDetails = clientService.loadUserByUsername(phone);

        //then
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals("name", userDetails.getUsername());
    }

    @Test
    void loadUserByUsernameWhenThrowException() {
        //Given
        String phone = "+00000000";
        Mockito.when(clientRepository.findByPhone(phone)).thenReturn(Optional.empty());
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            clientService.loadUserByUsername(phone);
        });

    }


    private Client getClientCorrect() {
        return Client.builder()
                .password("password")
                .birthday(LocalDate.now())
                .city("city")
                .name("name")
                .phone("+375292327431")
                .surname("surname")
                .patronymic("patronymic")
                .createdBy("createdBy")
                .createdAt(LocalDate.now())
                .modifiedAt(LocalDate.now())
                .modifiedBy(null)
                .build();
    }
}