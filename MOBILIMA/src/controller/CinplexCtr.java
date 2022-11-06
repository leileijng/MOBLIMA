package controller;

import model.Cinema;
import model.Cineplex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CinplexCtr {
    public static ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
    public static void loadCinplexFromFile(){
        try(BufferedReader br = new BufferedReader(new FileReader("data/cinplex.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Cineplex cineplex = new Cineplex();
                List<String> row_str = Arrays.asList(line.split(","));
                cineplex.setCinplexID(row_str.get(0).replaceAll("\\p{C}", ""));
                cineplex.setCinplexName(row_str.get(1).replaceAll("\\p{C}", ""));
                cineplex.setLocation(row_str.get(2).replaceAll("\\p{C}", ""));
                cineplexes.add(cineplex);
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        }
    }

    public static void loadCinemaFromFile(){
        try(BufferedReader br = new BufferedReader(new FileReader("data/cinema.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Cinema cinema = new Cinema();
                List<String> row_str = Arrays.asList(line.split(","));
                cinema.setCinemaCode(row_str.get(1).replaceAll("\\p{C}", ""));

                cinema.setClassOfCinema(Cinema.ClassOfCinema.valueOf(row_str.get(2).replaceAll("\\p{C}", "").toUpperCase()));
                cinema.setLayout(LayoutCtr.getLayoutById(row_str.get(3).replaceAll("\\p{C}", "")));
                for(Cineplex c : cineplexes){
                    if(c.getCinplexID().equals(row_str.get(0).replaceAll("\\p{C}", ""))){
                        c.addCinema(cinema);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        }
    }

    public static Cineplex getCinplexById(String cId){
        for(Cineplex c: cineplexes){
            if(c.getCinplexID().equals(cId)){
                return c;
            }
        }
        return null;
    }

}
