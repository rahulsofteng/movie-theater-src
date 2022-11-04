package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    LocalDateProvider dateProvider;

    private List<Show> shows;

    public Theater(LocalDateProvider dateProvider) {
        this.dateProvider = dateProvider;
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        shows = List.of(
                new Show(1, LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(9, 0)), LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(9, 0)).plusHours(1).plusMinutes(30), spiderMan),
                new Show(2, LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(11, 0)),LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(11, 0)).plusHours(1).plusMinutes(30), spiderMan),
                new Show(3, LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(12, 50)),LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(12, 50)).plusHours(1).plusMinutes(30), theBatMan),
                new Show(4, LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(14, 30)),LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(14, 30)).plusHours(1).plusMinutes(30), turningRed),
                new Show(5, LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(16, 10)),LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(16, 10)).plusHours(1).plusMinutes(30), spiderMan),
                new Show(6, LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(17, 50)),LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(17, 50)).plusHours(1).plusMinutes(30), theBatMan),
                new Show(7, LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(19, 30)),LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(19, 30)).plusHours(1).plusMinutes(30), turningRed),
                new Show(8, LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(21, 10)),LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(21, 10)).plusHours(1).plusMinutes(30), spiderMan),
                new Show(9, LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(23, 0)),LocalDateTime.of(dateProvider.currentDate(), LocalTime.of(23, 0)).plusHours(1).plusMinutes(30), theBatMan)
        );
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Show show;
        try {
            show = shows.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, show, howManyTickets);
    }

    public void printShows() {
        System.out.println(dateProvider.currentDate());
        System.out.println("===================================================");
        shows.forEach(s ->
                System.out.println(s.getShowId() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovie().getTicketPrice())
        );
        System.out.println("===================================================");
    }

    public void printShowsInJson(){
        shows.stream().forEach(System.out::println);
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.INSTANCE);
        theater.printShows();
    }
}
