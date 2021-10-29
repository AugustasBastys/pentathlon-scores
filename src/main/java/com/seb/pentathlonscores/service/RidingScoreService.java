package com.seb.pentathlonscores.service;

import com.seb.pentathlonscores.entity.RidingEvent;
import org.springframework.stereotype.Service;

@Service
public class RidingScoreService {

    private static final int BASE_POINTS = 1200;
    private static final int KNOCKING_PENALTY = 28;
    private static final int REFUSAL_PENALTY = 40;
    private static final int DISOBEDIENCE_PENALTY = 60;

    public int calculateRidingPoints(RidingEvent ridingEvent) {
        return BASE_POINTS - ridingEvent.getKnockingDown() * KNOCKING_PENALTY - ridingEvent.getRefusal() * REFUSAL_PENALTY
                - ridingEvent.getDisobedienceLeading() * DISOBEDIENCE_PENALTY;
    }
}
