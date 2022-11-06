package Services;

import model.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public abstract class Service {
    protected static List<Cinplex> cinplexes = new ArrayList<>();
    protected static List<Cinema> cinemas = new ArrayList<>();
    protected static List<Layout> layouts = new ArrayList<>();
    protected static List<Movie> movieList = new ArrayList<>();
    protected static List<Movie> showingMovieList = new ArrayList<>();
    protected static List<Session> sessions = new ArrayList<>();
    protected static List<MovieGoer> movieGoerList = new ArrayList<>();
    protected static List<Review> reviews = new ArrayList<>();
    protected static List<Payment> payments = new ArrayList<>();



    public static void initialiseFromFile(){
        movieGoerList = DataImport.importMoviegoers();
        layouts = DataImport.importLayouts();
        cinplexes = DataImport.loadCinplexFromFile();
        cinemas = DataImport.loadCinemaFromFile();
        movieList = DataImport.importMovies();
        sessions = DataImport.importSessions();
        reviews = DataImport.importReviews();
        payments = DataImport.importPayment();

        /*
        System.out.println("Number of cinplexes:" + cinplexes.size());
        System.out.println("Number of layouts:" + layouts.size());
        System.out.println("Number of movieList:" + movieList.size());
        System.out.println("Number of sessions:" + sessions.size());
        System.out.println("Number of reviews:" + reviews.size());
        System.out.println("Number of payments:" + payments.size());

         */
    }
    public void viewAllMovies(){
        for (Movie movie : showingMovieList) {
            System.out.println(movie.getMovieTitle());
        }
    }

    public void viewAllSessions(){
        int num = sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            sessions.get(i).viewDetails();
        }
    }

    public void viewSessionsByCinemaCode(String cinemaCode){
        int num = sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            if(sessions.get(i).getCinema().getCinemaCode().equals(cinemaCode))
                sessions.get(i).viewDetails();
        }
    }

    public void viewSessionsByMovieTitle(String movieTitle){
        int num = sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            if(sessions.get(i).getMovie().getMovieTitle().equals(movieTitle))
                sessions.get(i).viewDetails();
        }
    }

    public abstract void viewAllSessionsByFilter();
}
