package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.INSTANCE);
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 1, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(reservation.getTotalFee(), 38);
    }

    @Test
    void printMovieShow() {
        Theater theater = new Theater(LocalDateProvider.INSTANCE);
        theater.printShows();
    }

    @Test
    void printMovieShowInJson() {
        Theater theater = new Theater(LocalDateProvider.INSTANCE);
        theater.printShowsInJson();
    }
}
