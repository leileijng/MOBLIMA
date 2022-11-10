package model;


import java.util.Date;

public class StudentPrice extends Price{

    public StudentPrice(String movieType, String cinemaClass, int seatType, Date date) {
        super(movieType, cinemaClass, seatType, date);
    }

    @Override
    public double calculatePrice() {
        double price = 0;
        switch (priceTable.getSpecialRules("Student")){
            case OVERRIDE:
                price = priceTable.getPriceByType("Student") *
                        (this.isHoliday()?1*priceTable.getHolidayRate():1);;
                break;
            case DISCOUNT:
                 price = priceTable.getPriceByType("basic")
                         * priceTable.getDayRate(this.getWeekDay())
                         * priceTable.getCinemaClass(this.getCinemaClass())
                         * priceTable.getMovieTypeRate(this.getMovieType())
                         * priceTable.getSeatType(this.getSeatType())
                         * 0.8;
                break;
            case NORMAL:
                price = priceTable.getPriceByType("basic")
                        * priceTable.getDayRate(this.getWeekDay())
                        * priceTable.getCinemaClass(this.getCinemaClass())
                        * priceTable.getMovieTypeRate(this.getMovieType())
                        * priceTable.getSeatType(this.getSeatType());
                break;
        }
        return (double) Math.round(price * 100) / 100;
    }
}
