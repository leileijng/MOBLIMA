package Services;

import model.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Service {
    protected static List<Cineplex> cineplexes = new ArrayList<>();
    protected static List<Cinema> cinemas = new ArrayList<>();
    protected static List<Layout> layouts = new ArrayList<>();
    protected static List<Movie> movieList = new ArrayList<>();
    protected static List<Movie> showingMovieList = new ArrayList<>();
    protected static List<Session> sessions = new ArrayList<>();
    protected static List<MovieGoer> movieGoerList = new ArrayList<>();
    protected static List<Review> reviews = new ArrayList<>();
    protected static List<Payment> payments = new ArrayList<>();

    public static PriceTableService priceTable;

    public static void initialiseFromFile() {
        movieGoerList = DataImport.importMoviegoers();
        layouts = DataImport.importLayouts();
        cineplexes = DataImport.loadCinplexFromFile();
        cinemas = DataImport.loadCinemaFromFile();
        DataImport.importMovies();
        sessions = DataImport.importSessions();
        reviews = DataImport.importReviews();
        payments = DataImport.importPayment();
        priceTable = new PriceTableService();
    }

    public static void viewAllSessions(){
        int num = sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            sessions.get(i).viewDetails();
        }
    }

    public static void viewAllSessionsByCinplexName(String cinplexName){
        int num = sessions.size();
        for (int i = 0; i < num; i ++) {
            System.out.println(sessions.get(i).getCinema().getCineplex().getCinplexName());
            if(sessions.get(i).getCinema().getCineplex().getCinplexName().trim().equals(cinplexName.trim()))
                sessions.get(i).viewDetails();
        }
    }

    public static void viewAllSessionsByMovieTitle(String movieTitle){
        int num = sessions.size();
        for (int i = 0; i < num; i ++) {
            if(sessions.get(i).getMovie().getMovieTitle().equals(movieTitle))
                sessions.get(i).viewDetails();
        }
    }
}
