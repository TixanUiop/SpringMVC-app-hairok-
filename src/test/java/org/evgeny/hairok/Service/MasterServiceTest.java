package org.evgeny.hairok.Service;

import org.evgeny.hairok.Entity.MasterAccount;
import org.evgeny.hairok.Repository.MasterAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MasterServiceTest {

    @Mock
    private MasterAccountRepository masterAccountRepository;

    @InjectMocks
    private MasterService masterService;


    @Test
    void loadUserByUsernameIfUserExists() {

        //Given
        MasterAccount masterAccount = createMasterAccount();
        String email = "test@evgeny.org";
        Mockito.when(masterAccountRepository.findByEmail(email)).thenReturn(Optional.of(masterAccount));
        //When
        UserDetails userDetails = masterService.loadUserByUsername(email);
        //Then
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(userDetails.getUsername(), email);
    }

    @Test
    void loadUserByUsernameIfThrowExceptionIfUserDoesNotExist() {

        //Given
        String email = "tessdfsdfst@evgeny.org";
        Mockito.when(masterAccountRepository.findByEmail(email)).thenReturn(Optional.empty());
        Assertions.assertThrows(UsernameNotFoundException.class, () -> masterService.loadUserByUsername(email));
    }

    private MasterAccount createMasterAccount() {
        return MasterAccount.builder()
                .email("test@evgeny.org")
                .createdBy(null)
                .masterProfiles(null)
                .createdAt(null)
                .avatar(null)
                .password("password")
                .modifiedBy(null)
                .build();
    }


}