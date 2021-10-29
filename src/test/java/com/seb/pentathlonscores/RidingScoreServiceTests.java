package com.seb.pentathlonscores;

import com.seb.pentathlonscores.entity.RidingEvent;
import com.seb.pentathlonscores.service.RidingScoreService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RidingScoreServiceTests {

    private static final int BASE_POINTS = 1200;
    private static final int KNOCKING_PENALTY = 28;
    private static final int REFUSAL_PENALTY = 40;
    private static final int DISOBEDIENCE_PENALTY = 60;

    @Autowired
    private RidingScoreService ridingScoreService;

    @Test
    void checkWithNoPenalties() {
        Assert.assertEquals(BASE_POINTS, ridingScoreService.calculateRidingPoints(
                RidingEvent.builder()
                        .disobedienceLeading(0)
                        .refusal(0)
                        .knockingDown(0)
                        .build()
        ));
    }

    @Test
    void checkWithAllPenalties() {
        Assert.assertEquals(BASE_POINTS - KNOCKING_PENALTY - REFUSAL_PENALTY - DISOBEDIENCE_PENALTY,
                ridingScoreService.calculateRidingPoints(
                RidingEvent.builder()
                        .disobedienceLeading(1)
                        .refusal(1)
                        .knockingDown(1)
                        .build()
        ));
    }
}
