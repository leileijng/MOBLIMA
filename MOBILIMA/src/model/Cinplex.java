package model;
import java.io.*;
import java.util.*;

public class Cinplex {
    private String cineplexName;
    private String location;
    private ArrayList<Cinema> cinemas;


    public Cinplex(String cineplexName, String location){
        this.cineplexName = cineplexName;
        this.location = location;
        this.cinemas = new ArrayList<Cinema>();
    }

    public String getCineplexName() {
        return this.cineplexName;
    }

    public String getLocation() {
        return this.location;
    }

    public void viewCinemas() {
        System.out.println("count of the cineplex " + this.cineplexName + " : " + this.cinemas.size());
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
}