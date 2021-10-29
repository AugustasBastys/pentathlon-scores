package com.seb.pentathlonscores.service;

import com.seb.pentathlonscores.dto.AthleteDto;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
public class ConcludingEvetService {

    public List<AthleteDto> calculateTime(List<AthleteDto> athleteDtos) {

        athleteDtos.sort(Comparator.comparing(AthleteDto::getTotalScore).reversed());

        int leadingScore = athleteDtos.get(0).getTotalScore();

        athleteDtos.forEach( athleteDto -> {
            athleteDto.setConcludingEventTime(
                    athleteDto.getRunTime().plus(leadingScore - athleteDto.getTotalScore(), ChronoUnit.SECONDS)
            );
        });

        return athleteDtos;
    }

}
