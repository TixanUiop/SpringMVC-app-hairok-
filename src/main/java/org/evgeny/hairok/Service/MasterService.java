package org.evgeny.hairok.Service;

import lombok.RequiredArgsConstructor;
import org.evgeny.hairok.DTO.MasterProfilesDTO;
import org.evgeny.hairok.Entity.MasterAccount;
import org.evgeny.hairok.Entity.MasterProfiles;
import org.evgeny.hairok.Entity.SimpleGrantedAuthority;
import org.evgeny.hairok.Mapper.MasterProfilesToDTO;
import org.evgeny.hairok.Repository.MasterAccountRepository;
import org.evgeny.hairok.Repository.MasterProfilesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<MasterProfilesDTO> getPageAllMasterProfiles(Pageable pageable, String search, String searchByCity) {

        List<MasterProfilesDTO> allMasterProfiles = getAllMasterProfiles();

        if (search != null && !search.isBlank()) {
            String loverCase = search.toLowerCase();
            allMasterProfiles = allMasterProfiles.stream().filter(masterProfile -> masterProfile.getName().toLowerCase().contains(loverCase))
                    .toList();
        }

        if (searchByCity != null && !searchByCity.isBlank()) {
            String loverCase = searchByCity.toLowerCase();
            allMasterProfiles = allMasterProfiles.stream().filter(masterProfile -> masterProfile.getCity().toLowerCase().contains(loverCase))
                    .toList();
        }


        int sizeOfMasterProfiles = allMasterProfiles.size();
        int start = (int) pageable.getOffset();

        int end = Math.min((start + pageable.getPageSize()), sizeOfMasterProfiles);

        List<MasterProfilesDTO> pageContent = allMasterProfiles.subList(start, end);

        return new PageImpl<>(pageContent, pageable, sizeOfMasterProfiles);
    }




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
