package moblima;
import java.io.*;
import java.util.*;

class PriceTable {
    HashMap<String, double> movieType = new HashMap<String, double>();
    HashMap<String, double> cinemaClass = new HashMap<String, double>();
    HashMap<String, double> peopleGroup = new HashMap<String, double>();
    HashMap<String, double> specialDay = new HashMap<String, double>();

    public PriceTable(HashMap<String, double> movieType,
                      HashMap<String, double> cinemaClass,
                      HashMap<String, double> peopleGroup,
                      HashMap<String, double> specialDay) {
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