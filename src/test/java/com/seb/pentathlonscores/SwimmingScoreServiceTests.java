package com.seb.pentathlonscores;

import com.seb.pentathlonscores.service.SwimmingScoreService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@SpringBootTest
class SwimmingScoreServiceTests {

	private static final int ONE_SECOND = 1;
	private static final int ONE_SECOND_PENALTY_BONUS = 12;
	private static final int BASE_POINTS = 1000;
	private static final LocalTime TARGET_SWIMMING_TIME = LocalTime.of(0, 2, 30, 0);

	private LocalTime time;

	@Autowired
	private SwimmingScoreService swimmingScoreService;

	@Test
	void checkWithEvenTimeToTargetTime() {
		time = TARGET_SWIMMING_TIME;
		Assert.assertEquals(BASE_POINTS, swimmingScoreService.calculateSwimmingPoints(time));
	}

	@Test
	void checkWithTimeUnderTargetTime() {
		time = TARGET_SWIMMING_TIME.minus(ONE_SECOND, ChronoUnit.SECONDS);
		Assert.assertEquals(BASE_POINTS + ONE_SECOND_PENALTY_BONUS, swimmingScoreService.calculateSwimmingPoints(time));
	}

	@Test
	void checkWithTimeOverTargetTime() {
		time = TARGET_SWIMMING_TIME.plus(ONE_SECOND, ChronoUnit.SECONDS);
		Assert.assertEquals(BASE_POINTS - ONE_SECOND_PENALTY_BONUS, swimmingScoreService.calculateSwimmingPoints(time));
	}
}
