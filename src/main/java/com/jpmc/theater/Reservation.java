package com.jpmc.theater;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Reservation {
    private Customer customer;
    private Show show;
    private int audienceCount;

    public double getTotalFee() {
        return show.getShowFee() * audienceCount;
    }
}