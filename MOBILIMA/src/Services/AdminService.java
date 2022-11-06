package Services;

import interfaces.ManageMovie;
import interfaces.ManageSession;
import interfaces.SystemConfiguration;
import model.PriceTable;

import java.util.Scanner;

public class AdminService extends Service implements ManageMovie, ManageSession, SystemConfiguration {

    @Override
    public void addNewMovie() {
        //MovieService.addMovie();
        System.out.println("To do");
    }

    @Override
    public void updateMovieDetails() {
        //MovieService.editMovie();
        System.out.println("To do");
    }

    @Override
    public  void unlistMovie() {
        //MovieService.removeMovie();
        System.out.println("To do");

    }

    @Override
    public  void addNewSession() {
        SessionService.addSession();
    }

    @Override
    public  void updateSession() {
        SessionService.editSession();
    }

    @Override
    public  void delSession() {
        SessionService.removeSession();
    }

    @Override
    public  void configSystem() {
        //MoviegoerService.updateSortingStatus();
        System.out.println("To do");
    }

    @Override
    public  void updatePublicHoliday() {
        //PriceTableService.updateHolidayDates();
        System.out.println("To do");
    }

    @Override
    public void updatePriceTable() {

        System.out.println("To do");
    }

    /*
    @Override
    public void updatePriceTable() {
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
    }*/
}
