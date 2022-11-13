package model;

import service.Service;
import interfaces.ICalculatePrice;

import java.time.DayOfWeek;

/**
 * Represents a ticket price
 */
public abstract class Price implements ICalculatePrice{
    /**
     * movie type
     */
    private Ticket ticket;
    private Session session;
    private boolean isHoliday;

    /**
     * Constructor
     * @param ticket ticket
     * @param session session
     */
    public Price (Ticket ticket, Session session){
        this.ticket = ticket;
        this.session = session;
    }
    /**
     * gets the movie type
     * @return movietype
     */
    public String getMovieType() {
        return session.getMovie().getMovieType().toString();
    }

    /**
     *
     * @return cinema class
     */
    public String getCinemaClass() {
        return session.getCinema().getClassOfCinema().toString();
    }

    /**
     *
     * @return day of week
     */
    public DayOfWeek getWeekDay() {
        return new java.sql.Date(session.getStartTime().getTime()).toLocalDate().getDayOfWeek();
    }

    /**
     *
     * @return is holiday
     */
    public boolean isHoliday() {
        return Service.priceTable.isHoliday(
                java.sql.Date.valueOf(new java.sql.Date(session.getStartTime().getTime()).toLocalDate()));
    }

    /**
     *
     * @return seat type
     */
    public int getSeatType() {
        return this.ticket.getSeatType();
    }
}
