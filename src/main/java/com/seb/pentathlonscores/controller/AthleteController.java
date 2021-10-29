package com.seb.pentathlonscores.controller;

import com.seb.pentathlonscores.dto.AthleteDto;
import com.seb.pentathlonscores.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/athletes")
public class AthleteController {

    @Autowired
    private AthleteService placingService;

    @GetMapping("/placing")
    public List<AthleteDto> getAthletes() {
        return placingService.getAthletes();
    }

}
