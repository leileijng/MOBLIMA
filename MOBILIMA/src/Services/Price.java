package Services;

import Services.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public abstract class Price {
    private String movieType;
    private String cinemaClass;
    private Date date;
    private int seatType;

    public Price(String movieType, String cinemaClass, int seatType, Date date) {
        this.movieType = movieType;
        this.cinemaClass = cinemaClass;
        this.seatType = seatType;
        this.date = date;
    }
    public abstract double calculatePrice();

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
        return Service.priceTable.isHoliday(date);
    }

    public int getSeatType() {
        return seatType;
    }
}
