package org.evgeny.hairok.Mapper;

import org.evgeny.hairok.DTO.MasterProfilesDTO;
import org.evgeny.hairok.Entity.MasterProfiles;
import org.springframework.stereotype.Component;


@Component
public class MasterProfilesDTOToMasterProfiles implements Mapper<MasterProfilesDTO, MasterProfiles> {

    @Override
    public MasterProfiles map(MasterProfilesDTO masterProfilesDTO) {
        return MasterProfiles.builder()
                .name(masterProfilesDTO.getName())
                .city(masterProfilesDTO.getCity())
                .phone(masterProfilesDTO.getPhone())
                .birthday(masterProfilesDTO.getBirthday())
                .surname(masterProfilesDTO.getSurname())
                .patronymic(masterProfilesDTO.getPatronymic())
                .build();
    }
}
