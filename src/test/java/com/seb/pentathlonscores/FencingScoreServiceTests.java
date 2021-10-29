package com.seb.pentathlonscores;

import com.seb.pentathlonscores.service.FencingScoreService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FencingScoreServiceTests {

    private static final int NUMBER_OF_ATHLETES = 11;
    private static final int WINS = 7;
    private static final int BASE_POINTS = 1000;
    private static final int FENCING_BONUS_PENALTY_ONE_WIN = 40;

    @Autowired
    private FencingScoreService fencingScoreService;

    @Test
    void checkWithSeventyPercentWins() {
        Assert.assertEquals(BASE_POINTS, fencingScoreService.calculateFencingPoints(WINS, NUMBER_OF_ATHLETES));
    }

    @Test
    void checkWithUnderSeventyPercentWins() {
        Assert.assertEquals(BASE_POINTS - FENCING_BONUS_PENALTY_ONE_WIN, fencingScoreService.calculateFencingPoints(WINS - 1, NUMBER_OF_ATHLETES));
    }

    @Test
    void checkWithOverSeventyPercentWins() {
        Assert.assertEquals(BASE_POINTS + FENCING_BONUS_PENALTY_ONE_WIN, fencingScoreService.calculateFencingPoints(WINS + 1, NUMBER_OF_ATHLETES));
    }
}
