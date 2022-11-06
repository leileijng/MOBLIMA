package Services;

import interfaces.ManageMovie;
import interfaces.ManageSession;
import interfaces.SystemConfiguration;
import model.PriceTable;

import java.util.Scanner;

public class AdminService extends Service implements ManageMovie, ManageSession, SystemConfiguration {

    @Override
    public static void addNewMovie() {
        MovieService.addMovie();
    }

    @Override
    public static void updateMovieDetails() {
        MovieService.editMovie();
    }

    @Override
    public static void unlistMovie() {
        MovieService.removeMovie();
    }

    @Override
    public static void addNewSession() {
        SessionService.addSession();
    }

    @Override
    public static void updateSession() {
        SessionService.editSession();
    }

    @Override
    public static void delSession() {
        SessionService.removeSession();
    }

    @Override
    public static void configSystem() {
        MoviegoerService.updateSortingStatus();
    }

    @Override
    public static void updatePublicHoliday() {
        PriceTableService.updateHolidayDates();
    }

    @Override
    public static void updatePriceTable() {
        Scanner scan = new Scanner(System.in);
        int op;
        PriceTableService.viewPriceTable();
        System.out.println("Please enter the features you wanna update : ");
        System.out.println("1. movie type");
        System.out.println("2. cinema class");
        System.out.println("3. all price");
        System.out.println("4. week date");
        System.out.println("5. holiday");
        op = scan.nextInt();
        try {
            if (op == 1) PriceTableService.updateMovieType();
            if (op == 2) PriceTableService.updateCinemaClass();
            if (op == 3) PriceTableService.updatePrices();
            if (op == 4) PriceTableService.updateWeekDay();
            if (op == 5) PriceTableService.updateHolidayRate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
