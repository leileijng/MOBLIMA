package Services;

import model.Cinplex;
import model.Layout;
import model.Movie;
import model.Session;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public abstract class Service {
    protected static List<Cinplex> cinplexes = new ArrayList<>();
    protected static List<Layout> layouts = new ArrayList<>();
    protected static List<Movie> movieList = new ArrayList<>();
    protected static List<Movie> showingMovieList = new ArrayList<>();
    protected static List<Session> sessions = new ArrayList<Session>();

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

    public void viewAllSessions(){
        int num = sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            sessions.get(i).viewDetails();
        }
    }

    public abstract void viewAllSessionsByFilter();
}
