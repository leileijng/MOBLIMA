package model;
import java.io.*;
import java.util.*;

public class Ticket {
    private Session session;
    private int seatType;
    private String seatIndex;
    private double price;
    private Boolean booked;
    private String cinemaCode;

    public Ticket(Session session, int seatType, String seatIndex, double price, Boolean booked, String cinemaCode) {
        this.session = session;
        this.seatType = seatType;
        this.seatIndex = seatIndex;
        this.price = price;
        this.booked = booked;
        this.cinemaCode = cinemaCode;
    }

    public Ticket(String seatIndex, String cinemaCode) {
        this.seatIndex = seatIndex;
        this.booked = false;
        this.cinemaCode = cinemaCode;
    }

    public Boolean isBooked() {
        return this.booked;
    }
    
    public void assign(double price) {
        this.booked = true;
        this.price = price;
    }

    public String getSeatIndex() {
        return seatIndex;
    }

    public void setSeatIndex(String seatIndex) {
        this.seatIndex = seatIndex;
    }

    public Boolean getBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getSeatType() {
        return seatType;
    }

    public void setSeatType(int seatType) {
        this.seatType = seatType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCinemaCode() {
        return cinemaCode;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }
}