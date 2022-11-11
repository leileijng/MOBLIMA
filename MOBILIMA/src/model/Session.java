package model;
import java.sql.Timestamp;
import java.util.*;
/**
 * Represent a movie session
 */
public class Session {
    /**
     * session index of session
     */
    private String sessionIndex;
    /**
     * movie the session points to
     */
    private Movie movie;
    /**
     * cinema the session screens in
     */
    private Cinema cinema;
    /**
     * start time of session
     */
    private Timestamp startTime;
    /**
     * end time of session
     */
    private Timestamp endTime;
    /**
     * tickets contained in this session
     */
    private Vector<Ticket> tickets = new Vector<>();

    /**
     * constructor of session, which auto generates tickets
     * @param sessionIndex
     * @param movie
     * @param cinema
     * @param startTime
     * @param endTime
     */
    public Session(String sessionIndex, Movie movie, Cinema cinema, Timestamp startTime, Timestamp endTime) {
        this.sessionIndex = sessionIndex;
        this.movie = movie;
        this.cinema = cinema;
        this.startTime = startTime;
        this.endTime = endTime;
        for (int i = 0; i < cinema.getLayout().getRow(); i ++) {
            char rowIndex = (char)('A' + i);
            for (int j = 0; j < cinema.getLayout().getCol(); j ++) {
                if(cinema.getLayout().getSeats()[i][j] == 1 || cinema.getLayout().getSeats()[i][j] == 2){
                    tickets.add(new Ticket(Character.toString(rowIndex) + "" + j, cinema.getLayout().getSeats()[i][j]));
                }
            }
        }
    }

    /**
     * get the movie
     * @return the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * view the details of the session
     */
    public void viewDetails() {
        System.out.println("=========================================================");
        System.out.println("Session Index    : " + this.sessionIndex);
        System.out.println("Movie Name       : " + this.movie.getMovieTitle());
        System.out.println("Cineplex Name    : " + this.cinema.getCineplex().getCineplexName());
        System.out.println("Cinema Code      : " + this.cinema.getCinemaCode());
        System.out.println("Cinema Type      : " + this.cinema.getClassOfCinema().toString());
        System.out.println("Day of Week      : " + this.startTime.toLocalDateTime().getDayOfWeek());
        System.out.println("Movie Start Time : " + this.startTime);
        System.out.println("Movie End Time   : " + this.endTime);
    }

    /**
     * view the tickets of the session
     */
    public void viewTickets() {
        int cnt = 0;
        System.out.println("============= Screen ==============");
        System.out.print(" ");
        for (int i = 0; i < this.cinema.getLayout().getCol(); i ++) System.out.print(" " + i + " ");
        System.out.print("\n");
        for (int i = 0; i < this.cinema.getLayout().getRow(); i ++) {
            System.out.print((char)('A' + i));
            for (int j = 0; j < this.cinema.getLayout().getCol(); j ++) {
                if (this.cinema.getLayout().getSeats()[i][j] == 0) System.out.print("   "); 
                else {
                    if (this.tickets.get(cnt).isBooked()) System.out.print("[✔]");//Array reqiured but Vector<Ticket> found
                    else {
                        if(this.tickets.get(cnt).getSeatType() == 2)
                            System.out.print("[♛]");
                        else System.out.print("[ ]");
                    }
                    cnt ++;
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * occupy the seat and book the ticket
     * @param seatIndex
     * @param price
     * @return the message
     */
    public String occupySeat(String seatIndex, double price) {
        for (int i = 0; i < this.tickets.size(); i ++) {
            if (this.tickets.get(i).getSeatIndex().equals(seatIndex)) {
                if (this.tickets.get(i).isBooked()) return "The ticket is already booked.";
                this.tickets.get(i).assign(price);
                return "Ticket booking successful.";
            }
        }
        return "No such ticket at this session";
    }

    /**
     * find the ticket by the seat ID
     * @param seatIndex
     * @return the required ticket
     */
    public Ticket getTicketBySeatID(String seatIndex) {
        for (Ticket ticket : tickets) {
            if (ticket.getSeatIndex().equals(seatIndex)) {
                return ticket;
            }
        }
        return null;
    }

    /**
     * get the start time
     * @return
     */
    public Timestamp getStartTime() {
        return startTime;
    }

    /**
     * get the session index
     * @return
     */
    public String getSessionIndex() {
        return sessionIndex;
    }

    /**
     * set the session index
     * @param sessionIndex
     */
    public void setSessionIndex(String sessionIndex) {
        this.sessionIndex = sessionIndex;
    }

    /**
     * set the movie
     * @param movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * set the tickets
     * @param tickets
     */
    public void setTickets(Vector<Ticket> tickets) {
        this.tickets = tickets;
    }

    /**
     * get the cinema
     * @return
     */
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * set the cinema
     * @param cinema
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * set the start time
     * @param startTime
     */
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    /**
     * set the end time
     * @param endTime
     */
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    /**
     * get the tickets
     * @return
     */
    public Vector<Ticket> getTickets() {
        return tickets;
    }
}