package moblima;
import java.io.*;
import java.util.*;

class Session {
    private String sessionIndex;
    private Movie movie;
    private Cinema cinema;
    private Timestamp startTime;
    private Timestamp endTime;
    private Vector<Ticket> tickets = new Vector<>();

    public Session(String sessionIndex, Movie movie, Cinema cinema, Timestamp startTime, Timestamp endTime) {
        this.sessionIndex = sessionIndex;
        this.movie = movie;
        this.cinema = cinema;
        this.startTime = startTime;
        this.endTime = endTime;
        for (int i = 0; i < cinema.layout.row; i ++) {
            char rowIndex = (char)('A' + i);
            for (int j = 0; j < cinema.layout.col; j ++) {
                if(cinema.layout.seats[i][j] == 1){
                    tickets.add(new Ticket((String)(rowIndex) + (String)(j), cinema.cinemaCode));
                }
            }
        }
    }

    public void viewDetails() {
        System.out.println("=========================================================");
        System.out.println("Session Index    : " + this.sessionIndex);
        System.out.println("Movie Name       : " + this.movie.getName());
        System.out.println("Cinema Code      : " + this.cinema.getCinemaCode());
        System.out.println("Movie Start Time : " + this.startTime.getTime());
        System.out.println("Movie End Time   : " + this.endTime.getTime());
    }

    public void viewTickets() {
        int cnt = 0;
        System.out.print(" ");
        for (int i = 0; i < this.cinema.layout.col; i ++) System.out.print(" " + i + " ");
        System.out.print("\n");
        for (int i = 0; i < this.cinema.layout.row; i ++) {
            System.out.print((char)('A' + i));
            for (int j = 0; j < this.cinema.layout.col; j ++) {
                if (this.seats[i][j] == 0) System.out.print("   ");
                else {
                    if (this.tickets[cnt].isBooked()) System.out.print("[v]");
                    else System.out.print("[ ]");
                    cnt += 1;
                }
            }
            System.out.print("\n");
        }
    }

    public String occupySeat(String seatIndex, double price) {
        for (int i = 0; i < this.tickets.size(); i ++) {
            if (this.tickets[i].seatIndex == seatIndex) {
                if (this.tickets[i].isBooked()) return "The ticket is already booked.";
                this.tickets[i].assign(price);
                return "Ticket booking successful.";
            }
        }
        return "No such ticket at this session";
    }
}