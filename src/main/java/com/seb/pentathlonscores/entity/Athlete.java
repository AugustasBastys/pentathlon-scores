package com.seb.pentathlonscores.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Athlete {
    private String name;
    private int fencingVictories;
    private LocalTime swimmingTime;
    private RidingEvent ridingEvent;
    private int shootingScore;
    private LocalTime runTime;
    private int totalScore;
}