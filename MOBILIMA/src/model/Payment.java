package model;

public class Payment {
    private String TID;
    private Session session;
    private MovieGoer movieGoer;

    private double amount;

    public Payment(String TID, Session session, MovieGoer movieGoer, double amount) {
        this.TID = TID;
        this.session = session;
        this.movieGoer = movieGoer;
        this.amount = amount;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    public void setMovieGoer(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
    }

    public double getPrice() {
        return amount;
    }
}
