package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public abstract class Price {
    protected static PriceTable priceTable = new PriceTable();
    private String movieType;
    private String cinemaClass;
    private Date date;
    private int seatType;

    public Price(String movieType, String cinemaClass, int seatType, Date date) {
        priceTable = new PriceTable();
        this.movieType = movieType;
        this.cinemaClass = cinemaClass;
        this.seatType = seatType;
        this.date = date;
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
        return new java.sql.Date(date.getTime()).toLocalDate().getDayOfWeek();
    }
    public Date getDate() {
        return date;
    }

    public boolean isHoliday() {
        LocalDate today = LocalDate.now();
        return priceTable.isHoliday(date);
    }

    public int getSeatType() {
        return seatType;
    }
}
