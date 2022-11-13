package service;

import model.*;

import java.util.List;
import java.util.Scanner;

/**
 * Moviegoer Service
 */
public class MoviegoerService extends Service{
    /**
     * scanner
     */
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * the rank list by revenue
     */
    private static boolean canListByRevenue;
    /**
     * the rank list by ratings
     */
    private static boolean canListByRatings;

    /**
     * Given a username, check if it is in the database, return reference
     * if it is in the database
     * @param username username of the user to be found
     * @return MovieGoer object of the user
     */
    public MovieGoer getMovieGoerByName(String username) {
        for (MovieGoer movieGoer : movieGoerList) {
            if (movieGoer.getUsername().equals(username))
                return movieGoer;
        }
        return null;
    }

    /**
     * Add a moviegoer to the list to store
     * @param movieGoer movie-goer to be added
     */
    public void addMovieGoer(MovieGoer movieGoer) {
        movieGoerList.add(movieGoer);
    }

    /**
     * Update movie-goers accessibility to view movies
     * sorted by revenue or by ratings.
     */
    public static void updateSortingStatus() {
        System.out.println("Enter attribute to set: ");
        System.out.println("1: Whether User can list by revenue or not.");
        System.out.println("2: Whether User can list by ratings or not.");
        System.out.println();
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            updateListByRevenue();
        } else if (choice == 2) {
            updateListByRatings();
        } else {
            throw new IllegalArgumentException("Invalid input choice!");
        }
    }

    /**
     * Helper function for updateSortingStatus,
     * decide whether movie-goers can view by revenue or not
     */
    private static void updateListByRevenue() {
        System.out.println("Set User can list movies by revenue or not? " +
                "(Yes 1 / No 0)");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 0) {
            canListByRevenue = false;
        } else if (choice == 1) {
            canListByRevenue = true;
        } else {
            throw new IllegalArgumentException("Invalid input, should be binary.");
        }
    }

    /**
     * Helper function for updateSortingStatus,
     * decide whether movie-goers can view by ratings or not
     */
    private static void updateListByRatings() {
        System.out.println("Set User can list movies by ratings or not? " +
                "(Yes 1 / No 0)");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 0) {
            canListByRatings = false;
        } else if (choice == 1) {
            canListByRatings = true;
        } else {
            throw new IllegalArgumentException("Invalid input, should be binary.");
        }
    }

    /**
     * List Movies sorted by filter
     * @param filter attribute to be sorted (NA, revenue, ratings)
     */
    public void viewMovieByFilter(String filter) {
        switch (filter) {
            case "":
            case "NA":
                MovieService.printAllCurrentMovies(); // if no filter
                break;
            case "revenue":
                if (canListByRevenue)
                    MovieService.printTop5MovieByRevenue();
                else
                    System.out.println("Access denied!");
                break;
            case "ratings":
                if (canListByRatings)
                    MovieService.printTop5MovieByRatings();
                else
                    System.out.println("Access denied!");
                break;
            default:
                System.err.println("Invalid filter!");
                break;
        }
    }

    /**
     * get the layout by the id
     * @return the list of cineplex
     */
    public Movie searchMovieByName() {
        do {
            System.out.println("Enter the name of the movie you want to search: " +
                    "(-1 to quit)");
            String name = scanner.nextLine();
            if (name.equals("-1"))
                return null;
            Movie movie = MovieService.getMovieByName(name);
            if (movie == null) {
                System.out.println("Sorry cannot find the movie you entered.");
            }
            else {
                System.out.println("Movie found!");
                movie.printInfo();
                return movie;
            }
        } while (true);
    }

    /**
     * Make booking for movieGoer for movie
     * @param movieGoer user to book
     * @param movie movie to be booked
     * @return status of booking, 1 for success, 0 for fail
     */
    public int makeBookingByMovie(MovieGoer movieGoer, Movie movie) {
        return PaymentService.makeBookingByMovie(movieGoer, movie);
    }

    /**
     * Print all booking history of a movie-goer
     * @param movieGoer movie-goer to print history
     */
    public int printAllOrders(MovieGoer movieGoer) {
        List<Payment> payments = movieGoer.getPayments();
        int i = 0;
        if (payments == null || payments.size() == 0) {
            System.out.println("No history found!");
            return -1;
        }
        for (Payment payment : payments) {
            System.out.printf("=== For record %d ===\n", ++i);
            System.out.printf("Movie Name: %s\n", payment.getSession()
                    .getMovie().getMovieTitle());
            System.out.printf("Session Time: %s\n", payment.getSession()
                    .getStartTime().toString());
            System.out.printf("Transaction ID: %s\n", payment.getTID());
            System.out.printf("Price: %.2f\n", payment.getPrice());
        }
        return 1;
    }

    /**
     * Prompt the user to write review for a movie
     * @param movieGoer movie-goer to write the review
     * @param movie movie to be reviewed
     */
    public void writeReview(MovieGoer movieGoer, Movie movie) {
        double ratings = -1;
        do {
            System.out.println("Enter the rating of the movie: " +
                    "(between 1 to 5)");
            try {
                ratings = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Input should be a number!");
                continue;
            }
            if (ratings < 1 || ratings > 5)
                System.err.println("Input should be between 1 to 5!");
            else
                break;
        } while (true); // get ratings from the user
        System.out.println("Enter your comments: ");
        String comments = scanner.nextLine(); // get comments from the user
        ReviewService.addReview(movieGoer, movie, ratings, comments);
    }

    public void listAllReviewsByMovieGoer(MovieGoer movieGoer) {
        ReviewService.listAllReviewsByMovieGoer(movieGoer);
    }
}

