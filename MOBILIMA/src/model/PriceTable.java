package model;

import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;

public class PriceTable {
    HashMap<String,Double> movieType = new HashMap<String, Double>();
    HashMap<String,Double> cinemaClass = new HashMap<String, Double>();
    HashMap<String,Double> peopleGroup = new HashMap<String, Double>();
    HashMap<String,Double> weekDay = new HashMap<String, Double>();
    HashMap<String,Double> holiday = new HashMap<String, Double>();

    public PriceTable(HashMap<String, Double> movieType,
                      HashMap<String, Double> cinemaClass,
                      HashMap<String, Double> peopleGroup,
                      HashMap<String, Double> weekDay,
                      HashMap<String, Double> holiday) {
        this.movieType = movieType;
        this.cinemaClass = cinemaClass;
        this.peopleGroup = peopleGroup;
        this.weekDay = weekDay;
        this.holiday = holiday;
    }

    public void viewPriceTable() {
        System.out.println("=========================================================");
        System.out.println(">> Movie Type   :");
        System.out.println(this.movieType);
        System.out.println(">> Cinema Class :");
        System.out.println(this.cinemaClass);
        System.out.println(">> People Group :");
        System.out.println(this.peopleGroup);
        System.out.println(">> Week Day     :");
        System.out.println(this.weekDay);
        System.out.println(">> Holiday      :");
        System.out.println(this.holiday);
    }

    public void updateMovieType() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the movie type : ");
        String movieType = scan.next();
        System.out.println("Please enter the parameter : ");
        double para = scan.nextDouble();
        this.movieType.put(movieType, para);
    }

    public void updateCinemaClass() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the cinema class : ");
        String cinemaClass = scan.next();
        System.out.println("Please enter the parameter : ");
        double para = scan.nextDouble();
        this.cinemaClass.put(cinemaClass, para);
    }

    public void updatePeopleGroup() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the people group : ");
        String peopleGroup = scan.next();
        System.out.println("Please enter the parameter : ");
        double para = scan.nextDouble();
        this.peopleGroup.put(peopleGroup, para);
    }

    public void updateWeekDay() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the week day : ");
        String weekDay = scan.next();
        System.out.println("Please enter the parameter : ");
        double para = scan.nextDouble();
        this.weekDay.put(weekDay, para);
    }

    public void updateHoliday() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the week day : ");
        String holiday = scan.next();
        System.out.println("Please enter the parameter : ");
        double para = scan.nextDouble();
        this.holiday.put(holiday, para);
    }

    public double calculatePrice(String movieType, String cinemaClass, String peopleGroup, String weekDay, String holiday) {
        return this.movieType.get(movieType) * this.cinemaClass.get(cinemaClass) * this.peopleGroup.get(peopleGroup) * this.weekDay.get(weekDay) * this.holiday.get(holiday);
    }
}