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
     * @param TID Transaction ID
     * @param session session
     * @param movieGoer movie-goer
     * @param amount price amount
     */
    public Payment(String TID, Session session, MovieGoer movieGoer, double amount) {
        this.TID = TID;
        this.session = session;
        this.movieGoer = movieGoer;
        this.amount = amount;
    }

    /**
     * gets the transaction ID
     * @return TID transaction ID
     */
    public String getTID() {
        return TID;
    }

    /**
     * get the session ID
     * @return session ID
     */
    public Session getSession() {
        return session;
    }

    /**
     * get movie-goer
     * @return movie-goer
     */
    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    /**
     * Get price of the payment
     * @return price
     */
    public double getPrice() {
        return amount;
    }
}
