package Services;

import model.Movie;

public class MovieService {
    public static Movie getMovieByName(String movieTitle) {
        for(Movie m : Service.movieList){
            if(m.getMovieTitle().equals(movieTitle))
                return m;
        }
        return null;
    }
}
