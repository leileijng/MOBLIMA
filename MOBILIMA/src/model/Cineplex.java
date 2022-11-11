package model;
import java.util.*;

public class Cineplex {
    /**
     * cineplex
     */
    private String cineplexID;
    private String cineplexName;
    private String location;
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();;

    public Cineplex(String c_id, String cinplexName, String location) {
        cineplexID = c_id;
        this.cineplexName = cinplexName;
        this.location = location;
    }

    public Cineplex() {
    }

    public String getCinplexName() {
        return this.cineplexName;
    }

    public void setCinplexName(String cinplexName) {
        this.cineplexName = cinplexName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void viewCinemas() {
        System.out.println("count of the cinplex " + this.cineplexName + " : " + this.cinemas.size());
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
        return cineplexID;
    }

    public void setCinplexID(String c_id) {
        cineplexID = c_id;
    }
}