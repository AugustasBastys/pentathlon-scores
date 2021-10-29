package com.seb.pentathlonscores.service;

import com.seb.pentathlonscores.commonConstants.CommonConstants;
import org.springframework.stereotype.Service;

@Service
public class ShootingScoreService {

    private static final int TARGET_SCORE = 172;
    private static final int PENALTY_OR_BONUS = 12;

    public int calculateShootingPoints(int score) {
        return (score - TARGET_SCORE) * PENALTY_OR_BONUS + CommonConstants.BASE_POINTS;
    }

}
