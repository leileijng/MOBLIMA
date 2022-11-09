package Services;

import controller.MovieGoerCtr;
import interfaces.ManageMovie;
import interfaces.ManageSession;
import interfaces.SystemConfiguration;
import model.Price;
import model.PriceTable;

import java.util.Scanner;

public class AdminService extends Service implements ManageMovie, ManageSession, SystemConfiguration {

    @Override
    public void addNewMovie() {
        MovieService.addMovie();
    }

    @Override
    public void updateMovieDetails() {
        MovieService.editMovie();
    }

    @Override
    public void unlistMovie() {
        MovieService.removeMovie();
    }

    @Override
    public void addNewSession() {
        SessionService.addSession();
    }

    @Override
    public void updateSession() {
        SessionService.editSession();
    }

    @Override
    public void delSession() {
        SessionService.removeSession();
    }

    @Override
    public  void configSystem() {
        MoviegoerService.updateSortingStatus();
    }

    @Override
    public  void updatePublicHoliday() {
        PriceTable priceTable = Price.getPriceTable();
        priceTable.updateHolidayDates();
    }

    @Override
    public void updatePriceTable() {
        PriceTable priceTable = Price.getPriceTable();
        priceTable.updatePriceTable();
    }
}
