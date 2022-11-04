package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWith20PercentDiscount() {
        Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(10, 0);
        Show show = new Show(5, LocalDateTime.of(date, time), LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1).plusMinutes(30)), movie);
        assertEquals(10, movie.calculateTicketPrice(show));
    }

    @Test
    void specialMovieAnd11AmTo4PmDiscount() {
        Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(16, 0);
        Show show = new Show(5, LocalDateTime.of(date, time), LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1).plusMinutes(30)), movie);
        assertEquals(7.5, movie.calculateTicketPrice(show));
    }

    @Test
    void Dollar3DiscountForFirstShow() {
        Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(9, 0);
        Show show = new Show(1, LocalDateTime.of(date, time), LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1).plusMinutes(30)), movie);
        assertEquals(7, movie.calculateTicketPrice(show));
    }

    @Test
    void Dollar3DiscountForSecondShow() {
        Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(9, 0);
        Show show = new Show(2, LocalDateTime.of(date, time), LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1).plusMinutes(30)), movie);
        assertEquals(8, movie.calculateTicketPrice(show));
    }

    @Test
    void MovieBetween11AmTo4PmDiscount() {
        Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(16, 0);
        Show show = new Show(6, LocalDateTime.of(date, time), LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1).plusMinutes(30)), movie);
        assertEquals(7.5, movie.calculateTicketPrice(show));
    }

    @Test
    void MovieOn7thOfTheMonthDiscount() {
        Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 0);
        LocalDate date = LocalDate.of(2022, 11, 7);
        LocalTime time = LocalTime.of(9, 0);
        Show show = new Show(6, LocalDateTime.of(date, time), LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1).plusMinutes(30)), movie);
        assertEquals(9, movie.calculateTicketPrice(show));
    }

    @Test
    void MaximumPossibleDiscount() {
        Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1);
        LocalDate date = LocalDate.of(2022, 11, 7);
        LocalTime time = LocalTime.of(11, 0);
        Show show = new Show(1, LocalDateTime.of(date, time), LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(1).plusMinutes(30)), movie);
        assertEquals(7, movie.calculateTicketPrice(show));
    }
}
