package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFeeWithNoDiscountFor3Customers() {
        var customer = new Customer("John Doe", "1");
        var show = new Show(5,
                LocalDateTime.now(),
                LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1).plusMinutes(30)),
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0)
        );
        assertTrue(new Reservation(customer, show, 3).getTotalFee() == 30);
    }

    @Test
    void totalFeeWithAllFor3CustomersDiscount() {
        var customer = new Customer("John Doe", "1");
        var show = new Show(1,
                LocalDateTime.now(),
                LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1).plusMinutes(30)),
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1)
        );
        assertTrue(new Reservation(customer, show, 3).getTotalFee() == 21);
    }
}
