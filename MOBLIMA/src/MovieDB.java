package MOBLIMA;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A database of all movies, containing a list of all movies
 *
 */
public class MovieDB {
    List<Movie> movieList;
    public MovieDB() { movieList = new ArrayList<>(); }
    public void addMovie(Movie movie) { movieList.add(movie); }
    public void printTop5MovieByRevenue() {
        movieList.sort(Comparator.comparingDouble(Movie::getTotalRevenue).reversed());
        for (int i = 0; i < Math.min(5, movieList.size()); ++i) {
            movieList.get(i).printInfo();
        }
    }
    public void printTop5MovieByRatings() {
        movieList.sort(Comparator.comparingDouble(Movie::getOverallRating).reversed());
        for (int i = 0; i < Math.min(5, movieList.size()); ++i) {
            movieList.get(i).printInfo();
        }
    }

    public Movie getMovieByName(String name) {
        for (Movie movie : movieList) {
            if (movie.getMovieTitle().equals(name))
                return movie;
        }
        return null;
    }

    public static void main(String[] args) {
        MovieDB movieDB = new MovieDB();
        Movie[] movies = new Movie[6];
        for (int i = 0; i < 6; ++i) {
            movies[i] = new Movie("jvpr"+ i);
            movies[i].addReview(new Review(123, movies[i], i%5+1, "comment "+i));
            movieDB.addMovie(movies[i]);
        }
        movieDB.printTop5MovieByRatings();
    }
}
