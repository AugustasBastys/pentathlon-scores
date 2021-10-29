package com.seb.pentathlonscores.service;

import com.seb.pentathlonscores.dto.AthleteDto;
import com.seb.pentathlonscores.entity.Athlete;
import com.seb.pentathlonscores.mapper.AthleteMapper;
import com.seb.pentathlonscores.utils.AthleteCsvFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {

    @Autowired
    private AthleteCsvFileReader athleteCsvFileReader;
    @Autowired
    private AthleteMapper athleteMapper;
    @Autowired
    private ConcludingEvetService concludingEvetService;
    @Autowired
    private FencingScoreService fencingScoreService;
    @Autowired
    private RidingScoreService ridingScoreService;
    @Autowired
    private ShootingScoreService shootingScoreService;
    @Autowired
    private SwimmingScoreService swimmingScoreService;
    @Autowired
    private PlacingService placingService;

    public List<AthleteDto> getAthletes() {
        List<Athlete> athletes = athleteCsvFileReader.readAthleteFile();
        athletes = calculateScores(athletes);

        List<AthleteDto> athleteDtos = concludingEvetService.calculateTime(athleteMapper.entitiesToDtos(athletes));

        return placingService.sortByPlacing(athleteDtos);
    }

    private List<Athlete> calculateScores(List<Athlete> athletes) {

        int totalNumberOfAthletes = athletes.size();

        athletes.forEach( athlete -> {
            int totalScore = 0;

            totalScore += fencingScoreService.calculateFencingPoints(athlete.getFencingVictories(), totalNumberOfAthletes);
            totalScore += ridingScoreService.calculateRidingPoints(athlete.getRidingEvent());
            totalScore += shootingScoreService.calculateShootingPoints(athlete.getShootingScore());
            totalScore += swimmingScoreService.calculateSwimmingPoints(athlete.getSwimmingTime());

            athlete.setTotalScore(totalScore);
        });

        return athletes;
    }

}
