import controller.*;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Cinplex cinplex;
    public static ArrayList<Movie> movies;
    public static MovieGoer movieGoer;
    public static AdminStaff adminStaff;

    public static PriceTable priceTable;

    public static void main(String[] args) {
        initialiseData();


    }

    public static void initialiseData(){
        //an example of creating local objects and initialise data inside
        cinplex = new Cinplex("Golden Village", "Jurong Point");
        cinplex.addCinema(new Cinema("T1", Cinema.ClassOfCinema.MAX));
        CinplexCtr.cinplex = cinplex;

        SessionCtr.timetable = new Timetable();
        // new priceTable
    }

    public static void dataInport(){

    }
    public static void dataExport(){

    }

    public static void systemInit(){
        System.out.println("Please select mode:\n1. User Mode\t2.Admin Mode");

        Scanner scan = new Scanner(System.in);
        int sel = scan.nextInt();
        if(sel == 1) movieGoer = MoviegoerCtr.moviegoerLogin();
        else{
            adminStaff = AdminstaffCtr.AdminstaffLogin();
            adminStaffConsole(adminStaff);
        }
    }

    public static void movieGoerConsole(){}

    public static void updatePriceTable() {
        Scanner scan = new Scanner(System.in);
        int op;
        priceTable.viewPriceTable();
        System.out.println("Please enter the features you wanna update : ");
        System.out.println("1. movie type");
        System.out.println("2. cinema class");
        System.out.println("3. people group");
        System.out.println("4. week date");
        System.out.println("5. holiday");
        op = scan.nextInt();
        try {
            if (op == 1) priceTable.updateMovieType();
            if (op == 2) priceTable.updateCinemaClass();
            if (op == 3) priceTable.updatePeopleGroup();
            if (op == 4) priceTable.updateWeekDay();
            if (op == 5) priceTable.updateHoliday();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void adminStaffConsole(){
        Scanner scan = new Scanner(System.in);
        int op;
        while(true){
            System.out.println("Please enter your option of functions: ");
            System.out.println("1. create a new movie");
            System.out.println("2. update a existing movie");
            System.out.println("3. remove a existing movie");
            System.out.println("4. create a new session");
            System.out.println("5. update a existing session");
            System.out.println("6. remove a existing session");
            System.out.println("7. set movie goer configuration");
            System.out.println("8. view top 5 movies by Revenue");
            System.out.println("9. view top 5 movies by Ratings");
            System.out.println("10. update price table");
            System.out.println("11. log out");
            op = scan.nextInt();
            try {
                if (op == 1) MovieCtr.addMovie();
                if (op == 2) MovieCtr.editMovie();
                if (op == 3) MovieCtr.removeMovie();
                if (op == 4) SessionCtr.addSession();
                if (op == 5) SessionCtr.editSession();
                if (op == 6) SessionCtr.removeSession();
                if (op == 7) MovieGoerCtr.updateSortingStatus();
                if (op == 8) MovieCtr.printTop5MovieByRevenue();
                if (op == 9) MovieCtr.printTop5MovieByRatings();
                if (op == 10) updatePriceTable();
                if (op == 11) return ;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}