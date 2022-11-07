package model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;

public class PriceTable {
    private double holidayRate;
    private HashSet<Date> holiday = new HashSet<Date>();
    private HashMap<String,Double> prices = new HashMap<String, Double>();
    private HashMap<String,Double> movieType = new HashMap<String, Double>();
    private HashMap<String,Double> cinemaClass = new HashMap<String, Double>();
    private HashMap<DayOfWeek,Double> weekDay = new HashMap<DayOfWeek, Double>();
    enum PRICE_RULE {OVERRIDE, DISCOUNT, NORMAL};
    private HashMap<String,PRICE_RULE> specialRules = new HashMap<String, PRICE_RULE>();

    HashMap<Integer,Double> seatType = new HashMap<>();

    public PriceTable(){
        prices.put("Basic", 30.0);
        prices.put("Senior", 20.0);
        prices.put("Student", 25.0);
        movieType.put("3D", 1.1);
        movieType.put("Blockbuster", 1.05);
        movieType.put("2D", 1.0);
        movieType.put("IMAX", 1.2);
        cinemaClass.put(Cinema.ClassOfCinema.GOLD.toString(), 1.2);
        cinemaClass.put(Cinema.ClassOfCinema.MAX.toString(), 1.1);
        cinemaClass.put(Cinema.ClassOfCinema.NORMAL.toString(), 1.0);
        weekDay.put(DayOfWeek.MONDAY, 0.7);
        weekDay.put(DayOfWeek.TUESDAY, 0.8);
        weekDay.put(DayOfWeek.WEDNESDAY, 0.8);
        weekDay.put(DayOfWeek.THURSDAY, 0.9);
        weekDay.put(DayOfWeek.FRIDAY, 1.0);
        holidayRate = 1.2;
        seatType.put(1, 1.0);
        seatType.put(2, 1.2);
        specialRules.put("Senior",PRICE_RULE.OVERRIDE);
        specialRules.put("Student",PRICE_RULE.OVERRIDE);
    }

    public double getHolidayRate() {
        return holidayRate;
    }

    public double getPriceByType(String type){
        return prices.get(type);
    }

    public double getMovieTypeRate(String movie){
        return this.movieType.get(movie);
    }

    public double getCinemaClass(String cinema){
        return this.cinemaClass.get(cinema);
    }
    public double getDayRate(DayOfWeek day){
        return this.weekDay.get(day);
    }

    public void viewPriceTable() {
        System.out.println("=========================================================");
        System.out.println(">> Movie Type   :");
        System.out.println(this.movieType);
        System.out.println(">> Cinema Class :");
        System.out.println(this.cinemaClass);
        System.out.println(">> Week Day     :");
        System.out.println(this.weekDay);
        System.out.println(">> Holiday      :");
        //System.out.println(this.holiday);
    }

    public void updatePrices(){
        Scanner scan = new Scanner(System.in);
        for(String key : prices.keySet()){
            System.out.println("Update for "+ key +": (press 0 to skip)\n");
            double price;
            if((price=scan.nextDouble()) != 0) prices.put(key, price);
        }
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


    public void updateWeekDay() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the week day : ");
        String weekDay = scan.next();
        System.out.println("Please enter the parameter : ");
        double para = scan.nextDouble();
        this.weekDay.put(DayOfWeek.valueOf(weekDay), para);
    }

    public void updateSpecialRules() {
        String group = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Which group to update?\n1.Senior\t2.Student");
        int selGroup = scan.nextInt();
        System.out.print("Change to 1.Override\t2.Discount 80% \t3.No Special Price");
        int selection = scan.nextInt();
        if(selGroup==1) group = "Senior";
        else if(selGroup==2) group = "Student";
        else {
            System.err.println("Invalid Input");
            return;
        }
        if(selection==1) specialRules.put(group,PRICE_RULE.OVERRIDE);
        else if(selection==2) specialRules.put(group,PRICE_RULE.DISCOUNT);
        else if(selection==3) specialRules.put(group,PRICE_RULE.NORMAL);
        else {
            System.err.println("Invalid Input");
            return;
        }
    }

    public PRICE_RULE getSpecialRules(String group) {
        return specialRules.get(group);
    }

    //TODO add holiday date (and its relevant methods)
    public void updateHolidayDates() throws ParseException {
        System.out.println(this.holiday);
        System.out.println("What you wanna do about the holiday set?\n1.add a new date\t2.remove a existing date");
        Scanner scan = new Scanner(System.in);
        int op = scan.nextInt();
        if (op == 1) {
            String dateStr = scan.next();
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
            this.holiday.add(date);
        } else {
            String dateStr = scan.next();
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
            this.holiday.remove(date);
        }
    }

    public boolean isHoliday(Date date){
        return this.holiday.contains(date);
    }

    //TODO transform attributes type to enum when possible
}