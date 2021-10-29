package com.seb.pentathlonscores.utils;

import com.seb.pentathlonscores.entity.Athlete;
import com.seb.pentathlonscores.entity.RidingEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AthleteCsvFileReader {

    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String ATHLETE_RESULTS_PATH = USER_DIR + "/src/main/resources/files/Athlete_Results.csv";
    private static final String COMMA_DELIMITER = ",";
    private static final String COLON_DELIMITER = ":";
    private static final String DOT_DELIMITER = "\\.";

    public List<Athlete> readAthleteFile() {
        List<Athlete> athleteList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ATHLETE_RESULTS_PATH));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                athleteList.add(mapValuesToAthlete(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return athleteList;
    }

    private Athlete mapValuesToAthlete(String[] values) {
        return Athlete.builder()
                .name(values[0])
                .fencingVictories(Integer.parseInt(values[1]))
                .swimmingTime(parseStringTime(values[2]))
                .ridingEvent(
                        RidingEvent.builder()
                                .knockingDown(Integer.parseInt(values[3]))
                                .refusal(Integer.parseInt(values[4]))
                                .disobedienceLeading(Integer.parseInt(values[5]))
                                .build()
                )
                .shootingScore(Integer.parseInt(values[6]))
                .runTime(parseStringTime(values[7]))
                .build();
    }

    private LocalTime parseStringTime(String time) {
        String[] values = time.split(COLON_DELIMITER);

        if(values.length == 2) {
            String[] secondsAndMili = values[1].split(DOT_DELIMITER);
            return LocalTime.of(0, Integer.parseInt(values[0]), Integer.parseInt(secondsAndMili[0]),
                    TenthOfSecNanoConverter.tenthOfSecondToNano(Integer.parseInt(secondsAndMili[1])));

        }
        String[] secondsAndMili = values[2].split(DOT_DELIMITER);
        return LocalTime.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(secondsAndMili[0]),
                TenthOfSecNanoConverter.tenthOfSecondToNano(Integer.parseInt(secondsAndMili[1])));
    }
}