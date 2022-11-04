package controller;

import model.*;

import java.util.List;
import java.util.Scanner;

public class MoviegoerCtr {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean canListByRevenue;
    private static boolean canListByRatings;

    public static MovieGoer moviegoerLogin(){
        System.out.println("Please enter your username: ");
        Scanner scan = new Scanner(System.in);
        String userName = scan.next();

        return new MovieGoer(userName);
    }

    public static void printMainPanel(MovieGoer movieGoer) {
        System.out.println("1: View Movies");
        System.out.println("2: View My Reviews");
        System.out.println("3: View My Orders");
        System.out.println("4: Logout");
        System.out.println("Enter your choice: ");
        int select = Integer.parseInt(scanner.nextLine());
        while (select != 4) {
            switch (select) {
                case 1 -> {
                    int result = viewMoviePanel(movieGoer);
                    if (result == -1) {// when user selects quit
                        select = 4;
                    } else if (result == 0) {// when user selects go back
                        System.out.println("Enter your choice: ");
                        select = Integer.parseInt(scanner.nextLine());
                    }
                }
                case 2 -> listAllReviews(movieGoer);
                case 3 -> listAllOrders(movieGoer);
                default -> {
                    System.out.println("Invalid choice!");
                    System.out.println("Enter your choice: ");
                    select = Integer.parseInt(scanner.nextLine());
                }
            }
        }
    }

    /**
     * Users can view movie panels and view movies
     * @param movieGoer user
     * @return choice of the user, log out or return to last menu
     */
    public static int viewMoviePanel(MovieGoer movieGoer) {
        System.out.println("1: View All Current Movies");
        if (canListByRevenue)
            System.out.println("2: View Top 5 Movie by Revenue");
        if (canListByRatings)
            System.out.println("3: View Top 5 Movie by Ratings");
        System.out.println("0: Go Back");
        System.out.println("-1: Logout");
        System.out.println("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        while (true) {
            switch (choice) {
                case -1 -> { return -1; }
                case 0 -> { return 0; }
                case 1 -> {
                    int rtn = viewAllMovies(movieGoer);
                    if (rtn == 0) {
                        System.out.println("Enter your choice: ");
                        choice = Integer.parseInt(scanner.nextLine());
                    } else {
                        return -1; // logout after booking
                    }
                }
                case 2 -> MovieCtr.printTop5MovieByRevenue();
                case 3 -> MovieCtr.printTop5MovieByRatings();
                default -> {
                    System.out.println("Invalid choice!");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(scanner.nextLine());
                }
            }
        }
    }

    /**
     * User can check all current movies and make bookings.
     * @param movieGoer movieGoer to view all Movies
     * @return user choice, 1 for successfully made booking, -1 for failed to
     * make payment, 0 for going back to
     */
    public static int viewAllMovies(MovieGoer movieGoer) {
        List<Movie> movies = MovieCtr.getShowingMovieList();
        for (int i = 0; i < movies.size(); ++i) {
            System.out.printf("=== Movie #%d ===\n", i);
            movies.get(i).printInfo();
        }
        System.out.println("Choice 1: Make bookings");
        System.out.println("Choice 0: Going back");
        int choice = Integer.parseInt(scanner.nextLine());
        while (true) {
            switch (choice) {
                case 0 -> { return 0; }
                case 1 -> {
                    System.out.println("Enter #id of the movie you want to book:");
                    int movieID = Integer.parseInt(scanner.nextLine());
                    if (movieID >= 0 && movieID < movies.size()) {
                        return makeBooking(movieGoer, movies.get(movieID));
                    }
                }default -> {
                    System.out.println("Invalid choice!");
                    System.out.println("Choice 1: Make bookings");
                    System.out.println("Choice 0: Going back");
                    choice = Integer.parseInt(scanner.nextLine());
                }
            }
        }

    }

    /**
     * Make booking for movieGoer for movie
     * @param movieGoer user to book
     * @param movie movie to be booked
     * @return status of booking, 1 for success, 0 for fail
     */
    public static int makeBooking(MovieGoer movieGoer, Movie movie) {
        // TODO Print session info for a specific movie,
        //  now can only print sessions for all movies
        SessionCtr.showSessions();
        System.out.println("Enter session ID: ");
        // TODO change session prompt message for user
        Session session = SessionCtr.getSessionByIndex();
        System.out.println("Layout: ");
        // TODO print layout

        System.out.println("Select row of the seat: ");
        int row = Integer.parseInt(scanner.nextLine());
        System.out.println("Select col of the seat: ");
        int col = Integer.parseInt(scanner.nextLine());
        // TODO Add selected seats

        // TODO calculate price
        // double price = PriceTable.calculatePrice();
        // TODO user make payment
        // TODO add a payment controller?
        Ticket ticket = new Ticket(String.valueOf(row)+String.valueOf(col),
                session.getCinema().getCinemaCode());
        // TODO Transaction being generated in payment?
        Payment payment = new Payment("TBC", ticket);
        movieGoer.addPayments(payment);
        System.out.println("Booking has been made!");
        return 1;
    }

    public static void updateListByRevenue() {
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
    public static void updateListByRatings() {
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

    public static void listAllReviews(MovieGoer movieGoer) {
        List<Review> reviews = movieGoer.getReviews();
        for (Review review : reviews) {
            System.out.println("Ratings:\t"+review.getRating());
            System.out.println("Comments:\t"+review.getComments());
        }
    }

    public static void listAllOrders(MovieGoer movieGoer) {
        List<Payment> payments = movieGoer.getPayments();
        int i = 0;
        for (Payment payment : payments) {
            // TODO: Add ticket information
            // Ticket ticket = payment.getTicket();
            System.out.printf("=== For record %d ===\n", i);
            System.out.printf("Transaction ID: %s\n", payment.getTID());
            System.out.printf("Price: %.2f\n", payment.getPrice());
            ++i;
        }
        System.out.println("1: Add Review");
        System.out.println("-1: quit");
        System.out.println("Enter your choice");
        int choice = Integer.parseInt(scanner.nextLine());
        while (choice == 1) {
            System.out.println("Enter the order history you want to review:");
            int recordID = Integer.parseInt(scanner.nextLine());
            if (recordID >= 0 && recordID < payments.size()) {
                System.out.println("Enter your rating: (Integer from 1 to 5)");
                int ratings = Integer.parseInt(scanner.nextLine());
                if (!(ratings >= 1 && ratings <= 5)) {
                    System.out.println("Invalid rating input!");
                    continue;
                }
                System.out.println("Enter your comments: ");
                String comments = scanner.nextLine();
                // TODO add getMovie() in payment or ticket or add session in
                //  Booking History
                // Movie movie = payments.get(recordID).getMovie;
//                Review review = new Review(movieGoer, movie,
//                        ratings, comments);
//                movie.addReview(review);
            }
            System.out.println("1: Add Review");
            System.out.println("-1: quit");
            System.out.println("Enter your choice");
            choice = Integer.parseInt(scanner.nextLine());
        }
    }
}
