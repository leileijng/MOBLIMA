package model;

import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;

public class PriceTable {
    private double basePrice;
    private Set<Date> holidays;
    private HashMap<String,Double> movieType = new HashMap<String, Double>();
    private HashMap<String,Double> cinemaClass = new HashMap<String, Double>();
    private HashMap<String,Double> peopleGroup = new HashMap<String, Double>();
    private HashMap<String,Double> weekDay = new HashMap<String, Double>();

    private double holidayRate;

    HashMap<Integer,Double> seatType = new HashMap<>();

    public PriceTable(){
        basePrice = 30;
        movieType.put("3D", 1.1);
        cinemaClass.put(Cinema.ClassOfCinema.GOLD.toString(), 1.2);
        peopleGroup.put("Senior", 0.8);
        weekDay.put("Monday", 0.7);
        holidayRate = 1.2;
        seatType.put(1, 1.0);
        seatType.put(2, 1.2);
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
        //System.out.println(this.holiday);
    }

    public void updateBasePrice(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the base price : ");
        this.basePrice = scan.nextDouble();
    }

    public void updateHolidayRate(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the rate for holiday ticket : ");
        this.holidayRate = scan.nextDouble();
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

    public double calculatePrice(String movieType, String cinemaClass, String peopleGroup, String weekDay, String holiday) {
        return this.movieType.get(movieType) * this.cinemaClass.get(cinemaClass) * this.peopleGroup.get(peopleGroup) * this.weekDay.get(weekDay) * this.holiday.get(holiday);
    }

    //TODO add holiday date (and its relevant methods)
    //TODO transform attributes type to enum when possible
    //TODO reconstruct calculatePrice (based on today's day of week, holiday matching, <new param> seat type)


}