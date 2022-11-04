package com.jpmc.theater;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class Show {
    private int showId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Movie movie;

    public double getShowFee() {
        return movie.calculateTicketPrice(this);
    }
}
