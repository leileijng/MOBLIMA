package model;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

public class Session {
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
        for (int i = 0; i < cinema.getLayout().getRow(); i ++) {
            char rowIndex = (char)('A' + i);
            for (int j = 0; j < cinema.getLayout().getCol(); j ++) {
                if(cinema.getLayout().getSeats()[i][j] == 1 || cinema.getLayout().getSeats()[i][j] == 2){
                    tickets.add(new Ticket(Character.toString(rowIndex) + "" + j, cinema.getCinemaCode()));//Cannot convert int to string with (String)
                }
            }
        }
    }

    public void viewDetails() {
        System.out.println("=========================================================");
        System.out.println("Session Index    : " + this.sessionIndex);
        System.out.println("Movie Name       : " + this.movie.getMovieTitle());
        System.out.println("Cinema Code      : " + this.cinema.getCinemaCode());
        System.out.println("Movie Start Time : " + this.startTime);
        System.out.println("Movie End Time   : " + this.endTime);
    }

    public void viewTickets() {
        int cnt = 0;
        System.out.print(" ");
        for (int i = 0; i < this.cinema.getLayout().getCol(); i ++) System.out.print(" " + i + " ");
        System.out.print("\n");
        for (int i = 0; i < this.cinema.getLayout().getRow(); i ++) {
            System.out.print((char)('A' + i));
            for (int j = 0; j < this.cinema.getLayout().getCol(); j ++) {
                if (this.cinema.getLayout().getSeats()[i][j] == 0) System.out.print("   "); 
                else {
                    if (this.tickets.get(cnt).isBooked()) System.out.print("[v]");//Array reqiured but Vector<Ticket> found
                    else System.out.print("[ ]");
                    cnt += 1;
                }
            }
            System.out.print("\n");
        }
    }

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
    
     public String getSessionIndex() {
        return sessionIndex;
    }

    public void setSessionIndex(String sessionIndex) {
        this.sessionIndex = sessionIndex;
    }
    

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}