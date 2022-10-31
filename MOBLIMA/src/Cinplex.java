package moblima;
import java.io.*;
import java.util.*;

class Cinplex {
    private String cineplexName;
    private String location;
    private Cinema[] cinemas;
    private int numOfCinema;

    public Cinplex(String cineplexName, String location, int numOfCinema, Cinema[] cinemas) {
        this.cineplexName = cineplexName;
        this.location = location;
        cinemas = new Cinema[numOfCinema];
		for (int i = 0; i < numOfCinema; i ++){
			this.cinemas[i] = cinemas[i];
		}
    }

    public String getCineplexName() {
        return this.cineplexName;
    }

    public String getLocation() {
        return this.location;
    }

    public void viewCinemas() {
        System.out.println("count of the cineplex " + this.cineplexName + " : " + this.numOfCinema);
        for (int i = 0; i < numOfCinema; i ++) {
            this.cinemas[i].viewDetails();
        }
    }
}