package controller;

import model.Cinema;
import model.Cinplex;

public class CinplexCtr {
    private Cinplex cinplex;

    public static Cinema getCinema(String cinemaCode){
        return cinplex.getCinema(cinemaCode);
    }

    public staic void addCinema(Cinema cinema) {
        cinplex.addCinema(cinema);
    }
}
