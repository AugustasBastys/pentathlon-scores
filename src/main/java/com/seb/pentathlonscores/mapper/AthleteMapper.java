package com.seb.pentathlonscores.mapper;

import com.seb.pentathlonscores.dto.AthleteDto;
import com.seb.pentathlonscores.entity.Athlete;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AthleteMapper {

    public List<AthleteDto> entitiesToDtos(List<Athlete> athletes) {
        List<AthleteDto> athleteDtos = new ArrayList<>();
        athletes.forEach( athlete -> {
            AthleteDto athleteDto = new AthleteDto();

            athleteDto.setName(athlete.getName());
            athleteDto.setFencingVictories(athlete.getFencingVictories());
            athleteDto.setSwimmingTime(athlete.getSwimmingTime());
            athleteDto.setRidingEvent(athlete.getRidingEvent());
            athleteDto.setShootingScore(athlete.getShootingScore());
            athleteDto.setRunTime(athlete.getRunTime());
            athleteDto.setTotalScore(athlete.getTotalScore());

            athleteDtos.add(athleteDto);
        });

        return athleteDtos;
    }

}
