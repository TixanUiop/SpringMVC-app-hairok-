package org.evgeny.hairok.Service;

import lombok.RequiredArgsConstructor;
import org.evgeny.hairok.Entity.Client;
import org.evgeny.hairok.Entity.SimpleGrantedAuthority;
import org.evgeny.hairok.Repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Optional<Client> byPhone = clientRepository.findByPhone(phone);

        return byPhone.map(user -> new org.springframework.security.core.userdetails.User(
            user.getName(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        ))
            .orElseThrow(() -> new UsernameNotFoundException("Проверьте логин или пароль"));
        }
}
