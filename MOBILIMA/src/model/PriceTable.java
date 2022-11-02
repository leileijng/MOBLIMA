package model;

import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;

class PriceTable {
    HashMap<String,Double> movieType = new HashMap<String, Double>();
    HashMap<String,Double> cinemaClass = new HashMap<String, Double>();
    HashMap<String,Double> peopleGroup = new HashMap<String, Double>();
    HashMap<String,Double> specialDay = new HashMap<String, Double>();

    public PriceTable(HashMap<String, Double> movieType,
                      HashMap<String, Double> cinemaClass,
                      HashMap<String, Double> peopleGroup,
                      HashMap<String, Double> specialDay) {
        this.movieType = movieType;
        this.cinemaClass = cinemaClass;
        this.peopleGroup = peopleGroup;
        this.specialDay = specialDay;
    }

    public void viewPriceTable() {
        System.out.println("=========================================================");
        System.out.println(">> Movie Type   :");
        System.out.println(this.movieType);
        System.out.println(">> Cinema Class :");
        System.out.println(this.cinemaClass);
        System.out.println(">> People Group :");
        System.out.println(this.peopleGroup);
        System.out.println(">> Special Day  :");
        System.out.println(this.specialDay);   
    }

    public void updateMovieType(String movieType, double para) {
        this.movieType.put(movieType, para);
    }

    public void updateCinemaClass(String cinemaClass, double para) {
        this.cinemaClass.put(cinemaClass, para);
    }

    public void updatePeopleGroup(String peopleGroup, double para) {
        this.peopleGroup.put(peopleGroup, para);
    }

    public void updateSpecialDay(String specialDay, double para) {
        this.specialDay.put(specialDay, para);
    }

    public double calculatePrice(String movieType, String cinemaClass, String peopleGroup, String specialDay) {
        return this.movieType.get(movieType) * this.cinemaClass.get(cinemaClass) * this.peopleGroup.get(peopleGroup) * this.specialDay.get(specialDay);
    }
}