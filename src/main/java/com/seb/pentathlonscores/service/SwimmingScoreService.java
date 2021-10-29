package com.seb.pentathlonscores.service;

import com.seb.pentathlonscores.commonConstants.CommonConstants;
import com.seb.pentathlonscores.utils.SecondsMilliConverter;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MILLIS;

@Service
public class SwimmingScoreService {

    private static final double ONE_THIRD = 1.0/3;
    private static final int POINT_BONUS_PENALTY = 4;
    private static final LocalTime TARGET_TIME = LocalTime.of(0, 2, 30, 0);

    public int calculateSwimmingPoints(LocalTime time) {
        int compareTime = time.compareTo(TARGET_TIME);
        if(compareTime > 0) {
            int milliOver = (int) TARGET_TIME.until(time, MILLIS);
            return CommonConstants.BASE_POINTS - POINT_BONUS_PENALTY *
                    (oneThirdsInGiveSeconds(SecondsMilliConverter.milliToSeconds(milliOver)));
        } else if (compareTime < 0) {
            int milliUnder = (int) time.until(TARGET_TIME, MILLIS);
            return CommonConstants.BASE_POINTS + POINT_BONUS_PENALTY *
                    (oneThirdsInGiveSeconds(SecondsMilliConverter.milliToSeconds(milliUnder)));
        }
        return CommonConstants.BASE_POINTS;
    }

    private int oneThirdsInGiveSeconds(double seconds) {
       return  (int) Math.floor(seconds / ONE_THIRD);
    }

}
