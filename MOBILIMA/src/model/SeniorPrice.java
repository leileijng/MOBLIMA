package model;

import Services.Service;

import java.util.Date;
/**
 * Represents senior price for a ticket
 */
public class SeniorPrice extends Price {
    public SeniorPrice(Ticket ticket, Session session) {
        super(ticket, session);
    }

    /**
     * overriding class of calculate price
     * @return
     */
    @Override
    public double calculatePrice() {
        double price = 0;
        switch (Service.priceTable.getSpecialRules("Senior")){
            case OVERRIDE:
                price = Service.priceTable.getPriceByType("Senior") *
                    (this.isHoliday()?1*Service.priceTable.getHolidayRate():1);
                break;
            case DISCOUNT:
                price = Service.priceTable.getPriceByType("Basic")
                        * Service.priceTable.getDayRate(this.getWeekDay())
                        * Service.priceTable.getCinemaClass(this.getCinemaClass())
                        * Service.priceTable.getMovieTypeRate(this.getMovieType())
                        * Service.priceTable.getSeatType(this.getSeatType())
                        * (this.isHoliday()?1*Service.priceTable.getHolidayRate():1)
                        * 0.8;
                break;
            case NORMAL:
                price = Service.priceTable.getPriceByType("Basic")
                        * Service.priceTable.getDayRate(this.getWeekDay())
                        * Service.priceTable.getCinemaClass(this.getCinemaClass())*
                        (this.isHoliday()?1*Service.priceTable.getHolidayRate():1)
                        * Service.priceTable.getMovieTypeRate(this.getMovieType())
                        * Service.priceTable.getSeatType(this.getSeatType());
                break;
        }
        return (double) Math.round(price * 100) / 100;
    }
}
