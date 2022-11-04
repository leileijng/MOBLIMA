import controller.*;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static MovieGoer movieGoer;
    public static AdminStaff adminStaff;

    public static PriceTable priceTable;


    public static void main(String[] args) {
        initialiseData();
        systemInit();
    }

    public static void initialiseData() {
        CinplexCtr.cinplex = new Cinplex("Golden Village", "Jurong Point");
        CinplexCtr.cinplex.addCinema(new Cinema("T1", Cinema.ClassOfCinema.MAX));
        CinplexCtr.cinplex.addCinema(new Cinema("T2", Cinema.ClassOfCinema.GOLD));
        CinplexCtr.cinplex.viewCinemas();

        MovieCtr.addMovieToDB(new Movie("Her"));
        MovieCtr.addMovieToDB(new Movie("Spider Man"));
        MovieCtr.addMovieToDB(new Movie("Hello World"));

        SessionCtr.addSessionToDB(
                new Session( "Her_s1",
                        MovieCtr.getMovieByName("Her"),
                        CinplexCtr.cinplex.getCinema("T1"),
                        SessionCtr.convertStr2Time("2022-11-19 15:00:00"),
                        SessionCtr.convertStr2Time("2022-11-19 17:00:00")
                ));

        SessionCtr.addSessionToDB(
                new Session( "Her_s2",
                        MovieCtr.getMovieByName("Spider Man"),
                        CinplexCtr.cinplex.getCinema("T1"),
                        SessionCtr.convertStr2Time("2022-11-20 15:00:00"),
                        SessionCtr.convertStr2Time("2022-11-20 17:00:00")
                ));
        // new priceTable
    }

    public static void dataInport() {

    }

    public static void dataExport() {

    }

    public static void systemInit() {
        System.out.println("Please select mode:\n1. User Mode\t2.Admin Mode");

        Scanner scan = new Scanner(System.in);
        int sel = scan.nextInt();
        if (sel == 1) movieGoer = MoviegoerCtr.moviegoerLogin();
        else {
            adminStaff = AdminstaffCtr.AdminstaffLogin();
            adminStaffConsole();
        }
    }

    public static void movieGoerConsole() {
    }

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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void adminStaffConsole(){
        Scanner scan = new Scanner(System.in);
        int op = 0;
        boolean format = false;
        do {
            try {
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

                String op_s = scan.next();
                op = Integer.parseInt(op_s);
            }
            catch(Exception e) {
                System.err.println("Please enter valid selection!");
            }
            if (op > 11 || op < 1) {
                System.err.println("Please enter within the selection range (1-11)!");
            }
            else{
                if (op == 1) MovieCtr.addMovie();
                else if (op == 2) MovieCtr.editMovie();
                else if (op == 3) MovieCtr.removeMovie();
                else if (op == 4) SessionCtr.addSession();
                else if (op == 5) SessionCtr.editSession();
                else if (op == 6) SessionCtr.removeSession();
                    //else if (op == 7) MovieGoerCtr.updateSortingStatus();
                else if (op == 8) MovieCtr.printTop5MovieByRevenue();
                else if (op == 9) MovieCtr.printTop5MovieByRatings();
                else if (op == 10) updatePriceTable();
                else if (op == 11) {
                    System.out.println("System exits!");
                    break;
                }
            }
        } while (true);
    }
}