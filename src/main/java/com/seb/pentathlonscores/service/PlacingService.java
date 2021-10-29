package com.seb.pentathlonscores.service;

import com.seb.pentathlonscores.dto.AthleteDto;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PlacingService {

    public List<AthleteDto> sortByPlacing(List<AthleteDto> athleteDtos) {
        athleteDtos.sort(Comparator.comparing(AthleteDto::getConcludingEventTime));

        LocalTime timeToCompare;
        String placing;
        String sharedPlacing;

        for (int i = 0; i < athleteDtos.size(); i++) {
            timeToCompare = athleteDtos.get(i).getConcludingEventTime();

            for (int j = i + 1; j < athleteDtos.size(); j++) {

                if(!athleteDtos.get(j).getConcludingEventTime().equals(timeToCompare)) {
                    if(areTwoAthletesTimesNextToEachOther(i, j)) {
                        placing = String.valueOf(i + 1);
                        athleteDtos.get(i).setPlace(placing);
                        break;
                    }
                        sharedPlacing = (i + 1) + "-" + j;
                        setSharedPlacing(athleteDtos, i, j - 1, sharedPlacing);
                        i = j;
                        break;
                } else if(isAtheleteTheLastInTheList(athleteDtos, j)) {
                    sharedPlacing = (i + 1) + "-" + (j + 1);
                    setSharedPlacing(athleteDtos, i, j, sharedPlacing);
                    i = j;
                    break;
                }
            }
            setLastPlaceIfPlaceIsNull(athleteDtos, i);
        }

        return athleteDtos;
    }

    private boolean isAtheleteTheLastInTheList(List<AthleteDto> athleteDtos, int j) {
        return j == athleteDtos.size() - 1;
    }

    private boolean areTwoAthletesTimesNextToEachOther(int i, int j) {
        return j == (i + 1);
    }

    private void setLastPlaceIfPlaceIsNull(List<AthleteDto> athleteDtos, int i) {
        if(i == athleteDtos.size() - 1 && athleteDtos.get(i).getPlace() == null) {
            String placing;
            placing = String.valueOf(athleteDtos.size());
            athleteDtos.get(athleteDtos.size()-1).setPlace(placing);
        }
    }

    private void setSharedPlacing(List<AthleteDto> athleteDtos, int startIndex, int endIndex, String sharedPlacing) {
        for(int i = startIndex; i <= endIndex; i++) {
            athleteDtos.get(i).setPlace(sharedPlacing);
        }
    }
}
