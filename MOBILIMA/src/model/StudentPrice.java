package model;
import service.Service;

/**
 * Student price of a ticket
 */
public class StudentPrice extends Price{
    /**
     * constructor of StudentPrice
     * @param ticket ticket the price corresponds to
     * @param session session the price corresponds to
     */
    public StudentPrice(Ticket ticket, Session session) {
        super(ticket, session);
    }
    /**
     *
     * @return calculate price for student
     */
    public double calculatePrice() {
        double price = 0;
        switch (Service.priceTable.getSpecialRules("Student")){
            case OVERRIDE:
                price = Service.priceTable.getPriceByType("Student") *
                        (this.isHoliday()?1*Service.priceTable.getHolidayRate():1);;
                break;
            case DISCOUNT:
                 price = Service.priceTable.getPriceByType("Basic")
                         * Service.priceTable.getDayRate(this.getWeekDay())
                         * Service.priceTable.getCinemaClass(this.getCinemaClass())
                         * Service.priceTable.getMovieTypeRate(this.getMovieType())
                         * Service.priceTable.getSeatType(this.getSeatType())
                         * 0.8;
                break;
            case NORMAL:
                price = Service.priceTable.getPriceByType("Basic")
                        * Service.priceTable.getDayRate(this.getWeekDay())
                        * Service.priceTable.getCinemaClass(this.getCinemaClass())
                        * Service.priceTable.getMovieTypeRate(this.getMovieType())
                        * Service.priceTable.getSeatType(this.getSeatType());
                break;
        }
        return (double) Math.round(price * 100) / 100;
    }
}
