package model;

public class Payment {
    private String TID;
    private Ticket ticket;
    private MovieGoer movieGoer;

    private double amount;

    public Payment(String TID, Ticket ticket) {
        this.TID = TID;
        this.ticket = ticket;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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
