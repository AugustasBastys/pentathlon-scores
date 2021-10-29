package com.seb.pentathlonscores.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RidingEvent {
    private int knockingDown;
    private int refusal;
    private int disobedienceLeading;
}
