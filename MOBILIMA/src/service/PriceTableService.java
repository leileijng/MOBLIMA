package service;


import model.ClassOfCinema;
import model.SpecialPriceRule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;

/**
 * Price Table Service
 */
public class PriceTableService {
    /**
     * the holiday rate
     */
    private double holidayRate;
    /**
     * the hash set of holiday
     */
    private final HashSet<Date> holiday = new HashSet<>();
    /**
     * the hash map of base prices
     */
    private final HashMap<String,Double> prices = new HashMap<>();
    /**
     * the hash map of movie types rate
     */
    private final HashMap<String,Double> movieType = new HashMap<>();
    /**
     * the hash map of cinema classes rate
     */
    private final HashMap<String,Double> cinemaClass = new HashMap<>();
    /**
     * the hash map of week dates rate
     */
    private final HashMap<DayOfWeek,Double> weekDay = new HashMap<>();
    /**
     * the hash map of special rules rate
     */
    private final HashMap<String, SpecialPriceRule> specialRules = new HashMap<>();
    /**
     * the hash map of seat types rate
     */
    private final HashMap<Integer,Double> seatType = new HashMap<>();

    /**
     * load the data of price table
     */
    public PriceTableService(){
        prices.put("Basic", 30.0);
        prices.put("Senior", 20.0);
        prices.put("Student", 25.0);
        movieType.put("3D", 1.1);
        movieType.put("Blockbuster", 1.2);
        movieType.put("2D", 1.0);
        movieType.put("IMAX", 1.3);
        cinemaClass.put(ClassOfCinema.GOLD.toString(), 1.2);
        cinemaClass.put(ClassOfCinema.MAX.toString(), 1.1);
        cinemaClass.put(ClassOfCinema.NORMAL.toString(), 1.0);
        weekDay.put(DayOfWeek.MONDAY, 0.8);
        weekDay.put(DayOfWeek.TUESDAY, 0.8);
        weekDay.put(DayOfWeek.WEDNESDAY, 0.8);
        weekDay.put(DayOfWeek.THURSDAY, 0.9);
        weekDay.put(DayOfWeek.FRIDAY, 1.1);
        weekDay.put(DayOfWeek.SATURDAY, 1.2);
        weekDay.put(DayOfWeek.SUNDAY, 1.2);
        holidayRate = 1.5;
        seatType.put(1, 1.0);
        seatType.put(2, 1.2);
        specialRules.put("Senior",SpecialPriceRule.OVERRIDE);
        specialRules.put("Student",SpecialPriceRule.OVERRIDE);
        try {
            holiday.add(new SimpleDateFormat("dd/MM/yyyy").parse("31/10/2022"));
            holiday.add(new SimpleDateFormat("dd/MM/yyyy").parse("24/11/2022"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get the holiday rate
     * @return holiday rate
     */
    public double getHolidayRate() {
        return holidayRate;
    }

    /**
     * get the base price by the type
     * @param type type ticket
     * @return type rate
     */
    public double getPriceByType(String type){
        return prices.get(type);
    }

    /**
     * get the rate by the movie type
     * @param movie title of the movie
     * @return rate of the type of the input movie
     */
    public double getMovieTypeRate(String movie){
        return this.movieType.get(movie);
    }

    /**
     * get the rate by the cinema class
     * @param cinema cinema name
     * @return cinema
     */
    public double getCinemaClass(String cinema){
        return this.cinemaClass.get(cinema);
    }

    /**
     * get the rate by the week date
     * @param day day of the week
     * @return responding rate
     */
    public double getDayRate(DayOfWeek day){
        return this.weekDay.get(day);
    }

    /**
     * get the rate by the seat type
     * @param type type of the seat
     * @return the rate of the type of the seat
     */
    public double getSeatType(int type) { return seatType.get(type); }

    /**
     * view the price table
     */
    public void viewPriceTable() {
        System.out.println("=========================================================");
        for(String key : prices.keySet()){
            System.out.println("Base Price for " + key);
            System.out.println("SGD" + prices.get(key));
        }
        System.out.println("=========================================================");
        System.out.println(">> Movie Type   :");
        for(String key : movieType.keySet()){
            System.out.println("Current rate for " + key);
            System.out.println(movieType.get(key)+"x");
        }
        System.out.println("=========================================================");
        System.out.println(">> Cinema Class :");
        for(String key : cinemaClass.keySet()){
            System.out.println("Current rate for " + key);
            System.out.println(cinemaClass.get(key) + "x");
        }
        System.out.println("=========================================================");
        System.out.println(">> Week Day     :");
        System.out.println("Monday rate: " + weekDay.get(DayOfWeek.MONDAY) + "x");
        System.out.println("TUESDAY rate: " + weekDay.get(DayOfWeek.TUESDAY) + "x");
        System.out.println("WEDNESDAY rate: " + weekDay.get(DayOfWeek.WEDNESDAY) + "x");
        System.out.println("THURSDAY rate: " + weekDay.get(DayOfWeek.THURSDAY) + "x");
        System.out.println("FRIDAY rate: " + weekDay.get(DayOfWeek.FRIDAY) + "x");
        System.out.println("SATURDAY rate: " + weekDay.get(DayOfWeek.SATURDAY) + "x");
        System.out.println("SUNDAY rate: " + weekDay.get(DayOfWeek.SUNDAY) + "x");
        System.out.println("=========================================================");
        System.out.println(">> Seat Type    :");
        for(int key : seatType.keySet()){
            System.out.println("Current rate for type" + key);
            System.out.println(seatType.get(key) + "x");
        }
        System.out.println("=========================================================");
        System.out.println(">> Holiday      :");
        this.displayHoliday();
        System.out.println("Current Holiday Rate : ");
        System.out.println(this.holidayRate);
    }

    /**
     * update the base prices
     */
    public void updatePrices(){
        Scanner scan = new Scanner(System.in);
        for(String key : prices.keySet()){
            System.out.println("Update for "+ key +": (press 0 to skip)\n");
            double price;
            if((price=scan.nextDouble()) != 0) prices.put(key, price);
        }
    }

    /**
     * update the holiday rate
     */
    public void updateHolidayRate(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the rate for holiday ticket : ");
        this.holidayRate = scan.nextDouble();
    }

    /**
     * update the rate of movie type
     */
    public void updateMovieType() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the movie type : ");
        String movieType = scan.next();
        System.out.println("Please enter the parameter : ");
        double para = scan.nextDouble();
        this.movieType.put(movieType, para);
    }

    /**
     * update the rate of cinema class
     */
    public void updateCinemaClass() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the cinema class : ");
        String cinemaClass = scan.next();
        System.out.println("Please enter the parameter : ");
        double para = scan.nextDouble();
        this.cinemaClass.put(cinemaClass, para);
    }

    /**
     * update the rate of week date
     */
    public void updateWeekDay() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the week day : ");
        String weekDay = scan.next();
        System.out.println("Please enter the parameter : ");
        double para = scan.nextDouble();
        this.weekDay.put(DayOfWeek.valueOf(weekDay), para);
    }

    /**
     * update the rate of seat type
     */
    public void updateSeatType() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the seat type : ");
        int seatType = scan.nextInt();
        System.out.println("Please enter the parameter : ");
        double para = scan.nextDouble();
        this.seatType.put(seatType, para);
    }

    /**
     * update the special rules
     */
    public void updateSpecialRules() {
        String group;
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
        if(selection==1) specialRules.put(group,SpecialPriceRule.OVERRIDE);
        else if(selection==2) specialRules.put(group,SpecialPriceRule.DISCOUNT);
        else if(selection==3) specialRules.put(group,SpecialPriceRule.NORMAL);
        else {
            System.err.println("Invalid Input");
        }
    }

    /**
     * get the special rules
     * @param group group name to find Special Rule
     * @return Special Price Rule
     */
    public SpecialPriceRule getSpecialRules(String group) {
        return specialRules.get(group);
    }

    /**
     * display the holidays in the set
     */
    public void displayHoliday(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for(Date date:this.holiday){
            System.out.println(dateFormat.format(date));
        }
    }

    /**
     * update the holidays in the set
     * @throws ParseException if input has an error format
     */
    public void updateHolidayDates() throws ParseException {
        System.out.println("==== Holiday List ====");
        displayHoliday();
        System.out.println("What you wanna do about the holiday set?\n1.add a new date\t2.remove a existing date");
        Scanner scan = new Scanner(System.in);
        int op = scan.nextInt();
        String dateStr = scan.next();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        if (op == 1) {
            this.holiday.add(date);
        } else {
            this.holiday.remove(date);
        }
        System.out.println("==== Updated Holiday List ====");
        displayHoliday();
    }

    /**
     * check if it is the holiday
     * @param date date to check
     * @return if the date is a holiday
     */
    public boolean isHoliday(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for(Date d : this.holiday){
            if(df.format(d).equals(df.format(date))){
                return true;
            }
        }
        return false;
    }

    /**
     * update the price table
     */
    public void updatePriceTable() {
        Scanner scan = new Scanner(System.in);
        int op;
        System.out.println("Please enter the features you wanna update : ");
        System.out.println("1. base prices");
        System.out.println("2. movie types affect parameters");
        System.out.println("3. cinema classes affect parameters");
        System.out.println("4. week dates affect parameters");
        System.out.println("5. seat types affect parameters");
        System.out.println("6. holiday rate");
        System.out.println("7. special rules");
        op = scan.nextInt();
        try {
            if (op == 1) updatePrices();
            if (op == 2) updateMovieType();
            if (op == 3) updateCinemaClass();
            if (op == 4) updateWeekDay();
            if (op == 5) updateSeatType();
            if (op == 6) updateHolidayRate();
            if (op == 7) updateSpecialRules();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}