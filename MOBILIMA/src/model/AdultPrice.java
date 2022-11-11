package model;

import Services.Price;
import Services.Service;

import java.util.Date;

public class AdultPrice extends Price {
    /**
     * constructor of AdultPrice
     * @param movieType
     * @param cinemaClass
     * @param seatType
     * @param date
     */
    public AdultPrice(String movieType, String cinemaClass, int seatType, Date date) {
        super(movieType, cinemaClass, seatType, date);
    }

    /**
     * calculate Price and return price with 2 decimal place
     * @return
     */
    @Override
    public double calculatePrice() {
        System.out.println(this.getSeatType());
        double price = Service.priceTable.getPriceByType("Basic")
                * Service.priceTable.getDayRate(this.getWeekDay())
                * Service.priceTable.getCinemaClass(this.getCinemaClass())
                * Service.priceTable.getMovieTypeRate(this.getMovieType())
                * Service.priceTable.getSeatType(this.getSeatType())
                * (this.isHoliday()?1*Service.priceTable.getHolidayRate():1);
        return (double) Math.round(price * 100) / 100;
    }
}
