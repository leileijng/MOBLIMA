package model;
/**
 * Represents a payment transaction made by moviegoer
 */
public class Payment {
    /**
     * TID of Payment in format of XXXYYYYMMDDhhmm
     */
    private final String TID;
    /**
     * Session of the payment corresponds to
     */
    private Session session;
    /**
     * Moviegoer of the payment belongs to
     */
    private MovieGoer movieGoer;
    /**
     * amount paid in this payment
     */
    private double amount;

    /**
     * constructor of payment
     * @param TID
     * @param session
     * @param movieGoer
     * @param amount
     */
    public Payment(String TID, Session session, MovieGoer movieGoer, double amount) {
        this.TID = TID;
        this.session = session;
        this.movieGoer = movieGoer;
        this.amount = amount;
    }

    /**
     *
     * @return TID
     */
    public String getTID() {
        return TID;
    }

    public Session getSession() {
        return session;
    }

    public MovieGoer getMovieGoer() {
        return movieGoer;
    }
    public double getPrice() {
        return amount;
    }
}
