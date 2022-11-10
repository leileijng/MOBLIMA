package model;

import java.util.Date;

public class AdultPrice extends Price{
    public AdultPrice(String movieType, String cinemaClass, int seatType, Date date) {
        super(movieType, cinemaClass, seatType, date);
    }

    @Override
    public double calculatePrice() {
        double price = priceTable.getPriceByType("Basic")
                * priceTable.getDayRate(this.getWeekDay())
                * priceTable.getCinemaClass(this.getCinemaClass())
                * priceTable.getMovieTypeRate(this.getMovieType())
                * priceTable.getSeatType(this.getSeatType())
                * (this.isHoliday()?1*priceTable.getHolidayRate():1);
        return (double) Math.round(price * 100) / 100;
    }
}
