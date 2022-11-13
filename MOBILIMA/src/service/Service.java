package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Overall Service
 */
public abstract class Service {
    /**
     * list of cineplex
     */
    protected static List<Cineplex> cineplexes = new ArrayList<>();
    /**
     * list of cinemas
     */
    protected static List<Cinema> cinemas = new ArrayList<>();
    /**
     * list of layouts
     */
    protected static List<Layout> layouts = new ArrayList<>();
    /**
     * list of movies
     */
    protected static List<Movie> movieList = new ArrayList<>();
    /**
     * list of movies
     */
    protected static List<Movie> showingMovieList = new ArrayList<>();
    /**
     * list of sessions
     */
    protected static List<Session> sessions = new ArrayList<>();
    /**
     * list of moviegoers
     */
    protected static List<MovieGoer> movieGoerList = new ArrayList<>();
    /**
     * list of reviews
     */
    protected static List<Review> reviews = new ArrayList<>();
    /**
     * list of payments
     */
    protected static List<Payment> payments = new ArrayList<>();

    /**
     * price table service
     */
    public static PriceTableService priceTable;

    /**
     * initialise the files
     */
    public static void initialiseFromFile() {
        movieGoerList = DataImport.importMoviegoers();
        layouts = DataImport.importLayouts();
        cineplexes = DataImport.loadCineplexFromFile();
        cinemas = DataImport.loadCinemaFromFile();
        DataImport.importMovies();
        sessions = DataImport.importSessions();
        reviews = DataImport.importReviews();
        payments = DataImport.importPayment();
        priceTable = new PriceTableService();
    }

    /**
     * view all sessions
     */
    public static void viewAllSessions(){
        int num = sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            sessions.get(i).viewDetails();
        }
    }

    /**
     * view all sessions by cineplex name
     * @param cineplexName
     */
    public static void viewAllSessionsByCineplexName(String cineplexName){
        int num = sessions.size();
        for (int i = 0; i < num; i ++) {
            System.out.println(sessions.get(i).getCinema().getCineplex().getCineplexName());
            if(sessions.get(i).getCinema().getCineplex().getCineplexName().trim().equals(cineplexName.trim()))
                sessions.get(i).viewDetails();
        }
    }

    /**
     * view all sessions by movie title
     * @param movieTitle
     */
    public static void viewAllSessionsByMovieTitle(String movieTitle){
        int num = sessions.size();
        for (int i = 0; i < num; i ++) {
            if(sessions.get(i).getMovie().getMovieTitle().equals(movieTitle))
                sessions.get(i).viewDetails();
        }
    }
}
