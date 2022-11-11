package model;
/**
 * Ticket contained by a session
 */
public class Ticket {
    /**
     * session of ticket
     */
    private Session session;
    /**
     * seat type of a ticket
     */
    private int seatType;
    /**
     * seat index of a ticket
     */
    private String seatIndex;
    /**
     * price of a ticket
     */
    private double price;
    /**
     * booking status of a ticket
     */
    private Boolean booked;

    /**
     * constructor of ticket
     * @param seatIndex
     * @param cinemaCode
     * @param seatType
     */
    public Ticket(String seatIndex, int seatType) {
        this.seatIndex = seatIndex;
        this.booked = false;
        this.seatType = seatType;
    }

    /**
     *
     * @return booking status
     */
    public Boolean isBooked() {
        return this.booked;
    }

    /**
     * set price for a ticket
     * @param price
     */
    public void assign(double price) {
        this.booked = true;
        this.price = price;
    }

    /**
     *
     * @return seat index
     */
    public String getSeatIndex() {
        return seatIndex;
    }

    /**
     *
     * @return seat type
     */
    public int getSeatType() {
        return seatType;
    }

    /**
     *
     * @return price of ticket
     */
    public double getPrice() {
        return price;
    }

    /**
     * set price for a ticket
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}