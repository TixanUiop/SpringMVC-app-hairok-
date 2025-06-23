package org.evgeny.hairok.Mapper;

import org.evgeny.hairok.DTO.MasterProfilesDTO;
import org.evgeny.hairok.DTO.MasterRatingDTO;
import org.evgeny.hairok.Entity.MasterProfiles;
import org.evgeny.hairok.Entity.MasterRating;
import org.evgeny.hairok.Utill.AverageRating;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class MasterProfilesToDTO implements Mapper<MasterProfiles, MasterProfilesDTO> {



    @Override
    public MasterProfilesDTO map(MasterProfiles masterProfiles) {



        return MasterProfilesDTO.builder()
                .id(masterProfiles.getId())
                .name(masterProfiles.getName())
                .phone(masterProfiles.getPhone())
                .city(masterProfiles.getCity())
                .surname(masterProfiles.getSurname())
                .patronymic(masterProfiles.getPatronymic())
                .birthday(masterProfiles.getBirthday())
                .rating(mapRate(masterProfiles.getRate()))
                .averageRating(AverageRating.GetAverageRating(masterProfiles.getRate()))
                .build();
    }

    private List<MasterRatingDTO> mapRate(List<MasterRating> MasterRating) {
        if (MasterRating.isEmpty()) {
            return Collections.emptyList();
        }
        List<MasterRatingDTO> masterRatingDTO = new ArrayList<>();
        for (MasterRating masterRating : MasterRating) {

            MasterRatingDTO build = MasterRatingDTO.builder()
                    .client(masterRating.getClient())
                    .masterProfiles(masterRating.getMasterProfiles())
                    .rating(masterRating.getRating())
                    .build();

            masterRatingDTO.add(build);
        }
        return masterRatingDTO;
    }
}
