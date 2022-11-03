package model;
import java.io.*;
import java.util.*;

class Ticket {
    private String seatIndex;
    private double price;
    private Boolean booked;
    private String cinemaCode;

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
    
    
}