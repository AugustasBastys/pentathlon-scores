package com.seb.pentathlonscores.service;

import com.seb.pentathlonscores.commonConstants.CommonConstants;
import org.springframework.stereotype.Service;

@Service
public class FencingScoreService {

    private static final int MATCHES_22_TO_23_POINTS = 40;
    private static final double WINNING_PERCENTAGE = 0.7;

    public int calculateFencingPoints(int wins, int totalNumberAthletes) {
        double targetNumberOfBouts = calculateNumberOfNeededWins(totalNumberAthletes);
        int underOverWins;

        if(wins > targetNumberOfBouts) {
            underOverWins = (int) Math.ceil(Double.valueOf(wins) - targetNumberOfBouts);
            return CommonConstants.BASE_POINTS + underOverWins * MATCHES_22_TO_23_POINTS;
        } else if(wins < targetNumberOfBouts) {
            underOverWins = (int) Math.floor(targetNumberOfBouts - Double.valueOf(wins));
            return CommonConstants.BASE_POINTS - underOverWins * MATCHES_22_TO_23_POINTS;
        }
        return CommonConstants.BASE_POINTS;
    }

    private double calculateNumberOfNeededWins(int numberOfAthletes) {
        return (numberOfAthletes - 1) * WINNING_PERCENTAGE;
    }

}
