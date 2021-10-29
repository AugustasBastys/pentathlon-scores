package com.seb.pentathlonscores;

import com.seb.pentathlonscores.dto.AthleteDto;
import com.seb.pentathlonscores.service.PlacingService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PlacingServiceTests {

    private static final int HOW_MANY_EVEN_EVENT_TIMES = 4;
    private static final int HOW_MANY_UNEVEN_EVENT_TIMES = 4;
    private static final int HOW_MANY_EVEN_UNEVEN_EVENT_TIMES = 5;
    private static final String FIRST_PLACE = "1";
    private static final int HOURS = 0;
    private static final int MINUTES = 3;
    private static final String EQUAL_PLACING = FIRST_PLACE + "-" + HOW_MANY_EVEN_EVENT_TIMES;
    private static final String SOME_EQUAL_PLACING = FIRST_PLACE + "-" + (HOW_MANY_EVEN_UNEVEN_EVENT_TIMES - 1);

    private List<AthleteDto> athleteDtoList;
    private AthleteDto athleteDto;

    @Autowired
    private PlacingService placingService;

    @BeforeEach
    void initialize() {
        athleteDtoList = new ArrayList<>();
        athleteDto = new AthleteDto(LocalTime.of(HOURS, MINUTES));
        athleteDtoList.add(athleteDto);
    }

    @Test
    void checkWithEvenConcludingEventTimes() {

        fillAthleteDtosWithEvenTimes(HOW_MANY_EVEN_EVENT_TIMES);

        athleteDtoList = placingService.sortByPlacing(athleteDtoList);

        for(int i = 0; i < HOW_MANY_EVEN_EVENT_TIMES; i++) {
            Assert.assertEquals(EQUAL_PLACING, athleteDtoList.get(i).getPlace());
        }
    }

    @Test
    void checkWithAllUnevenConcludingEventTimes() {

        for(int i = 1; i < HOW_MANY_UNEVEN_EVENT_TIMES; i++) {
            athleteDtoList.add(new AthleteDto(LocalTime.of(HOURS, MINUTES + i)));
        }

        athleteDtoList = placingService.sortByPlacing(athleteDtoList);

        for(int i = 0; i < HOW_MANY_UNEVEN_EVENT_TIMES; i++) {
            Assert.assertEquals(String.valueOf(i + 1), athleteDtoList.get(i).getPlace());
        }
    }

    @Test
    void checkWithOneConcludingEventTime() {
        athleteDtoList = placingService.sortByPlacing(athleteDtoList);
        Assert.assertEquals(FIRST_PLACE, athleteDtoList.get(0).getPlace());
    }

    @Test
    void checkWithSomeEvenAndUnEvenConcludingEventTimes() {
        fillAthleteDtosWithEvenTimes(HOW_MANY_EVEN_UNEVEN_EVENT_TIMES - 1);

        athleteDtoList.add(new AthleteDto(LocalTime.of(HOURS, MINUTES + 1)));

        athleteDtoList = placingService.sortByPlacing(athleteDtoList);

        for(int i = 0; i < HOW_MANY_EVEN_UNEVEN_EVENT_TIMES - 1; i++) {
            Assert.assertEquals(SOME_EQUAL_PLACING, athleteDtoList.get(i).getPlace());
        }
        Assert.assertEquals(String.valueOf(athleteDtoList.size()), athleteDtoList.get(athleteDtoList.size() - 1).getPlace());

    }

    private void fillAthleteDtosWithEvenTimes(int numberOfTimes) {
        for(int i = 1; i < numberOfTimes; i++) {
            athleteDtoList.add(new AthleteDto(LocalTime.of(HOURS, MINUTES)));
        }
    }
}
