package org.evgeny.hairok.DTO;


import lombok.Builder;
import lombok.Data;
import org.evgeny.hairok.Entity.Client;
import org.evgeny.hairok.Entity.MasterProfiles;


@Data
@Builder
public class MasterRatingDTO {

    Client client;

    MasterProfiles masterProfiles;

    Integer rating;
}
