package com.seb.pentathlonscores.dto;

import com.seb.pentathlonscores.entity.Athlete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AthleteDto extends Athlete {
    private String place;
    private LocalTime concludingEventTime;

    public AthleteDto(LocalTime concludingEventTime) {
        this.setConcludingEventTime(concludingEventTime);
    }
}
