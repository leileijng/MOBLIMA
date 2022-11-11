package model;

import Services.Service;

import java.time.DayOfWeek;
import java.util.Date;
/**
 * Represents a ticket price
 */
public abstract class Price {
    /**
     * movie type
     */
    private String movieType;
    /**
     * cinema class
     */
    private String cinemaClass;
    /**
     * date of the session
     */
    private Date date;
    /**
     * seat type
     */
    private int seatType;

    /**
     * constructor of Price
     * @param movieType
     * @param cinemaClass
     * @param seatType
     * @param date
     */
    public Price(String movieType, String cinemaClass, int seatType, Date date) {
        this.movieType = movieType;
        this.cinemaClass = cinemaClass;
        this.seatType = seatType;
        this.date = date;
    }

    /**
     * abstract method for calculating price
     * @return
     */
    public abstract double calculatePrice();

    /**
     *
     * @return movietype
     */
    public String getMovieType() {
        return movieType;
    }

    /**
     *
     * @return cinema class
     */
    public String getCinemaClass() {
        return cinemaClass;
    }

    /**
     *
     * @return day of week
     */
    public DayOfWeek getWeekDay() {
        return new java.sql.Date(date.getTime()).toLocalDate().getDayOfWeek();
    }

    /**
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return is holiday
     */
    public boolean isHoliday() {
        return Service.priceTable.isHoliday(date);
    }

    /**
     *
     * @return seat type
     */
    public int getSeatType() {
        return seatType;
    }
}
