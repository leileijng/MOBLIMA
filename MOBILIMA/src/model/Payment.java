package model;

public class Payment {
    private String TID;
    private Ticket[] tickets;

    public Payment(String TID, Ticket[] tickets) {
        this.TID = TID;
        this.tickets = tickets;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }
}
