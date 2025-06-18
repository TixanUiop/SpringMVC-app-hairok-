package org.evgeny.hairok.Service;

import lombok.RequiredArgsConstructor;
import org.evgeny.hairok.Entity.MasterAccount;
import org.evgeny.hairok.Entity.SimpleGrantedAuthority;
import org.evgeny.hairok.Repository.MasterAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterService implements UserDetailsService {

    private final MasterAccountRepository masterAccountRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MasterAccount> byEmail = masterAccountRepository.findByEmail(email);

        return byEmail.map(masterAccount -> new org.springframework.security.core.userdetails.User(
                        masterAccount.getEmail(),
                        masterAccount.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Проверьте логин или пароль"));
    }
}
