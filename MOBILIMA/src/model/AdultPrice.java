package model;

import service.Service;

/**
 * Adult Price Class
 */
public class AdultPrice extends Price {
    /**
     * constructor of AdultPrice
     * @param ticket ticket the price corresponds to
     * @param session session the price corresponds to
     */
    public AdultPrice(Ticket ticket, Session session) {
        super(ticket, session);
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
