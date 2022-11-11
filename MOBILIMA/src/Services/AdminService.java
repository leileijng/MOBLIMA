package Services;

import interfaces.IManageMovie;
import interfaces.IManageSession;
import interfaces.ISystemConfiguration;


public class AdminService extends Service implements IManageMovie, IManageSession, ISystemConfiguration {

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
    public  void settingFilter() {
        MoviegoerService.updateSortingStatus();
    }

    @Override
    public  void updatePublicHoliday() {
        try {
            Service.priceTable.updateHolidayDates();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updatePriceTable() {
        Service.priceTable.viewPriceTable();
        Service.priceTable.updatePriceTable();
    }
}
