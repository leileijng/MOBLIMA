package model;


import Services.Price;
import Services.Service;

import java.util.Date;

public class StudentPrice extends Price {

    public StudentPrice(String movieType, String cinemaClass, int seatType, Date date) {
        super(movieType, cinemaClass, seatType, date);
    }

    @Override
    public double calculatePrice() {
        double price = 0;
        switch (Service.priceTable.getSpecialRules("Student")){
            case OVERRIDE:
                price = Service.priceTable.getPriceByType("Student") *
                        (this.isHoliday()?1*Service.priceTable.getHolidayRate():1);;
                break;
            case DISCOUNT:
                 price = Service.priceTable.getPriceByType("basic")
                         * Service.priceTable.getDayRate(this.getWeekDay())
                         * Service.priceTable.getCinemaClass(this.getCinemaClass())
                         * Service.priceTable.getMovieTypeRate(this.getMovieType())
                         * Service.priceTable.getSeatType(this.getSeatType())
                         * 0.8;
                break;
            case NORMAL:
                price = Service.priceTable.getPriceByType("basic")
                        * Service.priceTable.getDayRate(this.getWeekDay())
                        * Service.priceTable.getCinemaClass(this.getCinemaClass())
                        * Service.priceTable.getMovieTypeRate(this.getMovieType())
                        * Service.priceTable.getSeatType(this.getSeatType());
                break;
        }
        return (double) Math.round(price * 100) / 100;
    }
}
