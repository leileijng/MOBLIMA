package Services;

import model.Classification;
import model.Movie;
import model.Review;
import model.ShowingStatus;

import java.util.*;

public class MovieService extends Service {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Add movie to movie list and showing movie list in the database
     * @param movie movie to be added
     */
    public static void addMovieToDB(Movie movie) {
        Service.movieList.add(movie);
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
        updateMovieClassification(movie);
        updateMovieEndOfShowingDate(movie);
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
        System.out.println(" 5: edit movie classification");
        System.out.println(" 6: edit movie end of showing date");
        System.out.println("-1: quit");
        System.out.println("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1 -> updateMovieStatus(movie);
            case 2 -> updateMovieDirector(movie);
            case 3 -> updateMovieCasts(movie);
            case 4 -> updateMovieSynopsis(movie);
            case 5 -> updateMovieClassification(movie);
            case 6 -> updateMovieEndOfShowingDate(movie);
            default -> System.out.println("Invalid choice!");
        }
    }

    public static void removeMovie() {
        System.out.println("Enter movie title to remove: ");
        String title = scanner.nextLine();
        Movie movie = getMovieByName(title);
        if (movie == null)
            throw new IllegalArgumentException("Cannot find movie!");
        movie.setShowingStatus(ShowingStatus.ENDOFSHOWING);
        showingMovieList.remove(movie);
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
            System.out.printf("Enter cast no.%d: ", i+1);
            casts[i] = scanner.nextLine();
        }
        movie.setCasts(casts);
    }

    public static void updateMovieEndOfShowingDate(Movie movie) {
        do {
            System.out.println("Enter end of showing date for movie " +
                    movie.getMovieTitle() + "(format: YYYYMMDD): ");
            String input = scanner.nextLine();
            try {
                int year = Integer.parseInt(input.substring(0, 4));
                int month = Integer.parseInt(input.substring(4, 6));
                int day = Integer.parseInt(input.substring(6, 8));
                System.out.println("Set date: " + year + month + day);
                // movie.setDateEndOfShowing(year, month, day);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        } while (true);

    }

    public static void updateMovieStatus(Movie movie) {
        System.out.println("Enter showing status for movie " +
                movie.getMovieTitle() + ": ");
        System.out.println("1 for Coming Soon\t2 for Preview");
        System.out.println("3 for Now showing\t4 for End of Showing");
        int choice = Integer.parseInt(scanner.nextLine());
        ShowingStatus status;
        switch (choice) {
            case 1 -> status = ShowingStatus.COMMINGSOON;
            case 2 -> status = ShowingStatus.PREVIEW;
            case 3 -> status = ShowingStatus.NOWSHOWING;
            case 4 -> status = ShowingStatus.ENDOFSHOWING;
            default -> {
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

    public static void updateMovieClassification(Movie movie) {
        System.out.println("Enter the classification of the movie: ");
        System.out.println("1: G, 2: PG, 3: PG13, 4: NC16, 5: M18, 6: R21");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1 -> movie.setClassification(Classification.G);
            case 2 -> movie.setClassification(Classification.PG);
            case 3 -> movie.setClassification(Classification.PG13);
            case 4 -> movie.setClassification(Classification.NC16);
            case 5 -> movie.setClassification(Classification.M18);
            case 6 -> movie.setClassification(Classification.R21);
            default -> {
                System.out.println("Invalid input!");
                return;
            }
        }
    }

    public static void printTop5MovieByRevenue() {
        showingMovieList.sort(Comparator.
                comparingDouble(Movie::getTotalRevenue).reversed());
        System.out.println("Current Top 5 Movie By Revenue:");
        for (int i = 0; i < Math.min(5, Service.movieList.size()); ++i) {
            Movie movie = showingMovieList.get(i);
            System.out.printf("%d\tRevenue: %5.2f\tTitle: %s\n",
                    i+1, movie.getTotalRevenue(), movie.getMovieTitle());
        }
    }

    public static void printTop5MovieByRatings() {
        showingMovieList.sort(Comparator.
                comparingDouble(Movie::getOverallRating).reversed());
        System.out.println("Top 5 Movie By Ratings: ");
        for (int i = 0; i < Math.min(5, Service.movieList.size()); ++i) {
            Movie movie = showingMovieList.get(i);
            System.out.printf("%d\tRating:%5.1f\tTitle: %s\n",
                    i+1, movie.getOverallRating(), movie.getMovieTitle());
        }
    }

    public static void printAllCurrentMovies() {
        int i = 0;
        for (Movie movie : showingMovieList) {
            System.out.printf("%d\t", ++i);
            System.out.println(movie.getMovieTitle());
        }
    }

    public static void printAllMovies() {
        int i = 0;
        for (Movie movie : movieList) {
            System.out.printf("%d:\tstatus:%s\t\t", ++i,
                    movie.getShowingStatus().toString());
            System.out.println(movie.getMovieTitle());
        }
    }

    public static Movie getMovieByName(String name) {
        while (name == null || name.compareTo("") == 0) {
            System.out.println("Empty name input, please re-enter!");
            name = scanner.nextLine();
        }
        for (Movie movie : Service.movieList) {
            if (movie.getMovieTitle().equals(name))
                return movie;
        }
        return null;
    }

    public static List<Movie> getShowingMovieList() {
        return showingMovieList;
    }
    private static void refresh() {
        Date date = new Date(System.currentTimeMillis());
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
            movies[i].addReview(new Review("123", movies[i], i%5+1, "comment "+i));
            movies[i].addReview(new Review("456", movies[i], i%5+1, "comment "+i));

            movies[i].addRevenue(100 * i);
            movies[i].setDateEndOfShowing(2022, 10, 10);
            MovieService.addMovieToDB(movies[i]);
        }
        MovieService.printTop5MovieByRatings();
        MovieService.printTop5MovieByRevenue();
        MovieService.printAllCurrentMovies();
        MovieService.editMovie(); // set movie jvpr[0] to end of showing
        MovieService.printAllCurrentMovies();
        Movie jvpr = new Movie("jvpr");
        jvpr.setShowingStatus(ShowingStatus.NOWSHOWING);
        MovieService.addMovieToDB(jvpr);
        MovieService.printAllCurrentMovies();
        MovieService.editMovie(); // edit info for movie jvpr
        jvpr.printInfo();
    }

}
