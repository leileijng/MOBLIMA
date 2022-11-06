package model;
import java.io.*;
import java.util.*;

public class Cinplex {
    private String cinplexID;
    private String cinplexName;
    private String location;
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();;

    public Cinplex(String c_id, String cinplexName, String location) {
        cinplexID = c_id;
        this.cinplexName = cinplexName;
        this.location = location;
    }

    public Cinplex() {
    }

    public String getCinplexName() {
        return this.cineplexName;
    }

    public void setCinplexName(String cinplexName) {
        this.cinplexName = cinplexName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void viewCinemas() {
        System.out.println("count of the cinplex " + this.cinplexName + " : " + this.cinemas.size());
        for (int i = 0; i < cinemas.size(); i ++) {
            this.cinemas.get(i).viewDetails();
        }
    }

    public Cinema getCinema(String cinemaCode) {
        for (int i =0 ; i < cinemas.size(); i ++) {
            if(this.cinemas.get(i).getCinemaCode().equals(cinemaCode)){
                return this.cinemas.get(i);
            }
        }
        return null;
    }

    public void addCinema(Cinema cinema){
        this.cinemas.add(cinema);
    }

    public String getCinplexID() {
        return cinplexID;
    }

    public void setCinplexID(String c_id) {
        cinplexID = c_id;
    }
}