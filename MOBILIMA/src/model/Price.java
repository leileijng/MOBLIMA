package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public abstract class Price {
    protected static PriceTable priceTable;
    private String movieType;
    private String cinemaClass;
    private DayOfWeek weekDay;
    private boolean holiday;
    private int seatType;

    public Price(String movieType, String cinemaClass, int seatType) {
        priceTable = new PriceTable();
        this.movieType = movieType;
        this.cinemaClass = cinemaClass;
        this.seatType = seatType;
        LocalDate today = LocalDate.now();
        weekDay = today.getDayOfWeek();
        //TODO decide whether it is in the holiday;
    }
    public abstract double calculatePrice();

    public static PriceTable getPriceTable() {
        return priceTable;
    }

    public String getMovieType() {
        return movieType;
    }

    public String getCinemaClass() {
        return cinemaClass;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public boolean isHoliday() {
        return holiday;
    }

    public int getSeatType() {
        return seatType;
    }
}
