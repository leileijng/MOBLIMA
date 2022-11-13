package service;

import interfaces.IManageMovie;
import interfaces.IManageSession;
import interfaces.ISystemConfiguration;

/**
 * Admin Service
 */
public class AdminService extends Service implements IManageMovie, IManageSession, ISystemConfiguration {
    /**
     * add new movie
     */
    @Override
    public void addNewMovie() {
        MovieService.addMovie();
    }

    /**
     * update movie details
     */
    @Override
    public void updateMovieDetails() {
        MovieService.editMovie();
    }

    /**
     * unlist movie
     */
    @Override
    public void unlistMovie() {
        MovieService.removeMovie();
    }

    /**
     * add new session
     */
    @Override
    public void addNewSession() {
        SessionService.addSession();
    }

    /**
     * update session details
     */
    @Override
    public void updateSession() {
        SessionService.editSession();
    }

    /**
     * remove session
     */
    @Override
    public void delSession() {
        SessionService.removeSession();
    }

    /**
     * set filter
     */
    @Override
    public  void settingFilter() {
        MoviegoerService.updateSortingStatus();
    }

    /**
     * update public holiday
     */
    @Override
    public  void updatePublicHoliday() {
        try {
            Service.priceTable.updateHolidayDates();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * update price table
     */
    @Override
    public void updatePriceTable() {
        Service.priceTable.viewPriceTable();
        Service.priceTable.updatePriceTable();
    }
}
