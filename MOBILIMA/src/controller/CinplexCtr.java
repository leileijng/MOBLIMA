package controller;

import model.Cinema;
import model.Cinplex;

public class CinplexCtr {
    public static void addCinema(Cinplex cinplex){
        cinplex.addCinema(new Cinema("T1", Cinema.ClassOfCinema.MAX));
    }
}
