package controller;

import model.Movie;
import model.Review;
import model.ShowingStatus;
import java.util.*;

/**
 * A database of all movies, containing a list of all movies,
 * and supports different operations.
 */
public class MovieCtr {
    static Scanner scanner = new Scanner(System.in);
    static Date date = new Date(System.currentTimeMillis());
    static List<Movie> movieList = new ArrayList<>();
    static List<Movie> showingMovieList = new ArrayList<>();

    /**
     * Add movie to movie list and showing movie list in the database
     * @param movie
     */
    public static void addMovieToDB(Movie movie) {
        movieList.add(movie);
        if (movie.getShowingStatus() == ShowingStatus.NOWSHOWING ||
            movie.getShowingStatus() == ShowingStatus.PREVIEW)
            showingMovieList.add(movie);
    }
    public static void addMovie() {
        System.out.println("Enter movie title: ");
        String title = scanner.nextLine();
        Movie movie = new Movie(title);
        updateMovieStatus(movie);
        updateMovieDirector(movie);
        updateMovieCasts(movie);
        updateMovieSynopsis(movie);
        addMovieToDB(movie);
    }

    public static void editMovie() {
        System.out.println("Enter movie title to edit: ");
        String title = scanner.nextLine();
        Movie movie = getMovieByName(title);
        if (movie == null)
            throw new IllegalArgumentException("Cannot find movie!");
        System.out.println(" 1: edit movie status");
        System.out.println(" 2: edit movie director");
        System.out.println(" 3: edit movie casts");
        System.out.println(" 4: edit movie synopsis");
        System.out.println("-1: quit");
        System.out.println("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: updateMovieStatus(movie);
            break;
            case 2: updateMovieDirector(movie);
            break;
            case 3: updateMovieCasts(movie);
            break;
            case 4: updateMovieSynopsis(movie);
            break;
            default: System.out.println("Invalid choice!");
        }
    }

    public static void removeMovie() {
        System.out.println("Enter movie title to remove: ");
        String title = scanner.nextLine();
        Movie movie = getMovieByName(title);
        if (movie == null)
            throw new IllegalArgumentException("Cannot find movie!");

    }
    public static void updateMovieDirector(Movie movie) {
        System.out.println("Enter director of the movie: ");
        String director = scanner.nextLine();
        movie.setDirector(director);
    }
    public static void updateMovieSynopsis(Movie movie) {
        System.out.println("Enter synopsis of the movie: ");
        String synopsis = scanner.nextLine();
        movie.setSynopsis(synopsis);
    }
    public static void updateMovieCasts(Movie movie) {
        System.out.println("Enter number of the casts: (>= 2)");
        int sz = Integer.parseInt(scanner.nextLine());
        String[] casts = new String[sz];
        for (int i = 0; i < sz; ++i) {
            System.out.println("Enter cast %d: ");
            casts[i] = scanner.nextLine();
        }
        movie.setCasts(casts);
    }

    public static void updateMovieStatus(Movie movie) {
        System.out.println("Enter showing status of the movie:");
        System.out.println("1 for Coming Soon\t2 for Preview");
        System.out.println("3 for Now showing\t4 for End of Showing");
        int choice = Integer.parseInt(scanner.nextLine());
        ShowingStatus status;
        switch (choice) {
            case 1: status = ShowingStatus.COMMINGSOON;
            break;
            case 2: status = ShowingStatus.PREVIEW;
            break;
            case 3: status = ShowingStatus.NOWSHOWING;
            break;
            case 4: status = ShowingStatus.ENDOFSHOWING;
            break;
            default: {
                System.out.println("Invalid choice!");
                return;
            }
        }
        if ((movie.getShowingStatus() == ShowingStatus.PREVIEW ||
            movie.getShowingStatus() == ShowingStatus.NOWSHOWING) &&
            status == ShowingStatus.ENDOFSHOWING)
            showingMovieList.remove(movie);
        movie.setShowingStatus(status);
    }

    public static void printTop5MovieByRevenue() {
        showingMovieList.sort(Comparator.comparingDouble(Movie::getTotalRevenue).reversed());
        System.out.println("Current Top 5 Movie By Revenue:");
        for (int i = 0; i < Math.min(5, movieList.size()); ++i) {
            Movie movie = showingMovieList.get(i);
            System.out.printf("%d\tTitle:%s\tRevenue:%.2f\n",
                    i, movie.getMovieTitle(), movie.getTotalRevenue());
        }
    }

    public static void printTop5MovieByRatings() {
        showingMovieList.sort(Comparator.comparingDouble(Movie::getOverallRating).reversed());
        System.out.println("Top 5 Movie By Ratings: ");
        for (int i = 0; i < Math.min(5, movieList.size()); ++i) {
            Movie movie = showingMovieList.get(i);
            System.out.printf("%d\tTitle:%s\tRating:%.1f\n",
                    i, movie.getMovieTitle(), movie.getOverallRating());
        }
    }

    public static void printAllCurrentMovies() {
        for (Movie movie : showingMovieList) {
            System.out.println(movie.getMovieTitle());
        }
    }

    public static Movie getMovieByName(String name) {
        for (Movie movie : movieList) {
            if (movie.getMovieTitle().equals(name))
                return movie;
        }
        return null;
    }

    private static void refresh() {
        date.setTime(System.currentTimeMillis());
        for (Movie movie : showingMovieList) {
            if (movie.getDateEndOfShowing().compareTo(date) > 0) {
                movie.setShowingStatus(ShowingStatus.ENDOFSHOWING);
                showingMovieList.remove(movie);
            }
        }
    }
    public static void main(String[] args) {
        Movie[] movies = new Movie[6];
        for (int i = 0; i < 6; ++i) {
            movies[i] = new Movie("jvpr"+ i);
            movies[i].addReview(new Review(123, movies[i], i%5+1, "comment "+i));
            movies[i].addReview(new Review(456, movies[i], i%5+1, "comment "+i));

            movies[i].addRevenue(100 * i);
            movies[i].setDateEndOfShowingDate(2022, 10, 10);
            MovieCtr.addMovieToDB(movies[i]);
        }
        MovieCtr.printTop5MovieByRatings();
        MovieCtr.printTop5MovieByRevenue();
        MovieCtr.printAllCurrentMovies();
        MovieCtr.editMovie(); // set movie jvpr[0] to end of showing
        MovieCtr.printAllCurrentMovies();
    }

    public static Movie getMovie(String movieTitle) {
        for(Movie m : movieList){
            if(m.getMovieTitle().equals(movieTitle)){
                return m;
            }
        }
        return null;
    }
}
