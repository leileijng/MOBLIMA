package Services;

import model.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public abstract class Service {
    protected static List<Cinplex> cinplexes = new ArrayList<>();
    protected static List<Layout> layouts = new ArrayList<>();
    protected static List<Movie> movieList = new ArrayList<>();
    protected static List<Movie> showingMovieList = new ArrayList<>();
    protected static List<Session> sessions = new ArrayList<Session>();

    protected static PriceTable priceTable = new PriceTable();


    public static void initialiseFromFile(){
        layouts = DataImport.importLayouts();
        cinplexes = DataImport.loadCinplexFromFile();
        DataImport.loadCinemaFromFile(cinplexes);
    }
    public void viewAllMovies(){
        for (Movie movie : showingMovieList) {
            System.out.println(movie.getMovieTitle());
        }
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
            if(sessions.get(i).getCinplx().getCinplexName().equals(cinplexName))
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
