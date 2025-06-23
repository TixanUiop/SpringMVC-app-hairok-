package org.evgeny.hairok.Utill;

import lombok.experimental.UtilityClass;
import org.evgeny.hairok.Entity.MasterProfiles;
import org.evgeny.hairok.Entity.MasterRating;

import java.util.List;

@UtilityClass
public class AverageRating {

    public double GetAverageRating( List<MasterRating> ratings) {
        if (ratings.isEmpty()) {
            return 0;
        }
        int size = ratings.size();
        double sum = 0;

        for (MasterRating mr : ratings) {
            sum += mr.getRating();
        }
        return sum / size;
    }

}
