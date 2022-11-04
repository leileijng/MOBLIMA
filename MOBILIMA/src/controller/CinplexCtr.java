package controller;

import model.Cinema;
import model.Cinplex;

import java.util.ArrayList;

public class CinplexCtr {
    public static ArrayList<Cinplex> cinplexes = new ArrayList<Cinplex>();
    public static void addCinplex(){
        cinplexes.add(new Cinplex("G01","Golden Village", "Jurong East"));
        cinplexes.add(new Cinplex("G02","Golden Village", "Jurong Point"));
        cinplexes.add(new Cinplex("G03","Golden Village", "Somerset 313"));
    }

    public static Cinplex getCinplexById(String cId){
        for(Cinplex c: cinplexes){
            if(c.getC_id().equals(cId)){
                return c;
            }
        }
        return null;
    }

}
