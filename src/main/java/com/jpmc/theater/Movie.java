package com.jpmc.theater;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.MonthDay;
import java.util.List;

@Data
@AllArgsConstructor
public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public double calculateTicketPrice(Show show) {
        this.ticketPrice = ticketPrice - getDiscount(show);
        return ticketPrice;
    }

    private double getDiscount(Show show) {
        double specialDiscount = 0;
        int day = show.getStartTime().getDayOfMonth();
        int time = show.getStartTime().getHour();
        //The discount amount applied only one if met multiple rules; biggest amount one
        if(time>=11 && time<=16)
            specialDiscount = Math.max(specialDiscount, ticketPrice * 0.25); //Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
        if (MOVIE_CODE_SPECIAL == specialCode)
            specialDiscount = Math.max(specialDiscount, ticketPrice * 0.2); //20% discount for the special movie
        if(show.getShowId() == 1)
            specialDiscount = Math.max(specialDiscount, 3); //$3 discount for the movie showing 1st of the day
        if(show.getShowId() == 2)
            specialDiscount = Math.max(specialDiscount, 2); //$2 discount for the movie showing 2nd of the day
        if(day == 7)
            specialDiscount = Math.max(specialDiscount, 1); // Any movies showing on 7th, you'll get 1$ discount
        return specialDiscount;
    }
}