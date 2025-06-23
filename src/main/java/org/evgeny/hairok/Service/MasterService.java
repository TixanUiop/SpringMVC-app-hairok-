package org.evgeny.hairok.Service;

import lombok.RequiredArgsConstructor;
import org.evgeny.hairok.DTO.MasterProfilesDTO;
import org.evgeny.hairok.Entity.MasterAccount;
import org.evgeny.hairok.Entity.MasterProfiles;
import org.evgeny.hairok.Entity.SimpleGrantedAuthority;
import org.evgeny.hairok.Mapper.MasterProfilesToDTO;
import org.evgeny.hairok.Repository.MasterAccountRepository;
import org.evgeny.hairok.Repository.MasterProfilesRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterService implements UserDetailsService {

    private final MasterAccountRepository masterAccountRepository;
    private final MasterProfilesRepository masterProfilesRepository;

    private final MasterProfilesToDTO mapperMasterProfilesToDTO;






    public List<MasterProfilesDTO> getAllMasterProfiles() {
        List<MasterProfiles> allMasters = masterProfilesRepository.findAll();

        if (allMasters.isEmpty()) {
            return Collections.emptyList();
        }

        List<MasterProfilesDTO> list = allMasters.stream()
                .map(mapperMasterProfilesToDTO::map)
                .toList();

        return list;
    }

    //TODO разобрать пагинацию
//    public List<MasterProfilesDTO> getPageAllMasterProfiles(Pageable pageable) {
//        List<MasterProfilesDTO> allMasterProfiles = getAllMasterProfiles();
//
//        int sizeOfMasterProfiles = allMasterProfiles.size();
//        int start = pageable.getNumberOfPages()
//
//    }




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
