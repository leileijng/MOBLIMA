package controller;

import Services.MovieService;
import Services.MoviegoerService;
import model.Movie;
import model.MovieGoer;
import java.util.List;
import java.util.Scanner;

public class MovieGoerCtr {
    public static Scanner scanner = new Scanner(System.in);
    public static MoviegoerService moviegoerService = new MoviegoerService();

    /**
     * Find a movieGoer with the username and password,
     * if the username cannot be found, create a new one
     * @return an movie-goer object
     */
    public static MovieGoer movieGoerLogin() {
        String username;
        String password;
        boolean access = false;
        MovieGoer movieGoer = null;
        do {
            System.out.println("Please enter your username: (enter -1 to quit)");
            username = scanner.nextLine();
            if (username.equals("-1"))
                return null;
            if((movieGoer = moviegoerService.getMovieGoerByName(username))!=null){
                System.out.println("Please enter your password: ");
                password = scanner.nextLine();
                if(password.equals("12345678")) access = true;
            }
            else access = true;
        } while (!access);

        if (movieGoer == null) {
            System.out.println("Hi," + username + "! Nice to meet you!");
            movieGoer = new MovieGoer(username);
            moviegoerService.addMovieGoer(movieGoer);
        }
        else{
            System.out.println("Welcome back " + username + "!");
        }
        return movieGoer;
    }

    /**
     * Print main prompt panel for movie goer
     * @param movieGoer user using the panel
     * @return status, 1 success, 0 go back, -1 logout
     */
    public static int printMainPanel(MovieGoer movieGoer) {
        int choice = 0;
        do {
            try {
                System.out.println(" 1: View/Search Movies");
                System.out.println(" 2: View My Reviews");
                System.out.println(" 3: View My Orders");
                System.out.println(" 0: Go back");
                System.out.println("-1: Logout");
                System.out.println("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Invalid choice!");
            }
            if (choice < -1 || choice > 4) {
                System.err.println("Please choice between -1 to 4!");
            } else {
                switch (choice) {
                    case -1 -> {
                        System.out.println("Logging out...");
                        return -1;
                    }
                    case 0 -> {
                        System.out.println("Going back...");
                        return 0;
                    }
                    case 1 -> {
                        int rnt  = viewMoviePanel(movieGoer);
                        if (rnt == -1) {
                            System.out.println("Logging out...");
                            return -1;
                        }
                    }
                    case 2 -> {
                        int rnt = viewReviewPanel(movieGoer);
                        if (rnt == -1) {
                            System.out.println("Logging out...");
                            return -1;
                        }
                    }
                    case 3 -> {
                        int rnt = viewOrdersPanel(movieGoer);
                        if (rnt == -1) {
                            System.out.println("Logging out... ");
                            return -1;
                        }
                    }
                }
            }
        } while (true);
    }

    /**
     * Users can view movie panels and view movies
     * After viewing all current movies, they can do bookings.
     * @param movieGoer user
     * @return status, 1 success, -1 log out or 0 return to last menu
     */
    public static int viewMoviePanel(MovieGoer movieGoer) {
        int choice = 0;
        do {
            try {
                System.out.println("1: View All Current Movies");
                System.out.println("2: View Top 5 Movie by Revenue");
                System.out.println("3: View Top 5 Movie by Ratings");
                System.out.println("4: Search Movie by Name");
                System.out.println("0: Go Back");
                System.out.println("-1: Logout");
                System.out.println("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            }  catch (Exception e) {
                System.err.println("Input choice should be an integer!");
            }
            if (choice < -1 || choice > 4) {
                System.err.println("Choice should be between -1 to 4!");
            } else {
                switch (choice) {
                    case -1 -> { return -1; }
                    case  0 -> { return 0; }
                    case  1 -> moviegoerService.viewMovieByFilter("");
                    case  2 -> moviegoerService.viewMovieByFilter("revenue");
                    case  3 -> moviegoerService.viewMovieByFilter("ratings");
                    case  4 -> moviegoerService.searchMovieByName();
                }
                if (choice == 1)
                    return bookingPanel(movieGoer);
            }
        } while (true);
    }

    /**
     * Booking panel for movie-goer
     * @param movieGoer user making the booking
     * @return status, 1 success, 0 go back, -1 logout
     */
    public static int bookingPanel(MovieGoer movieGoer) {
        int choice;
        do {
            System.out.println(" 1: Book movies");
            System.out.println(" 0: Return to last menu");
            System.out.println("-1: Log out");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Choice should be an integer!");
                continue;
            }
            if (choice < -1 || choice > 1) {
                System.err.println("Choice should be between -1 to 1");
            } else if (choice == -1 || choice == 0) {
                return choice;
            } else {
                moviegoerService.viewMovieByFilter("");
                int movieID;
                List<Movie> movieList = MovieService.getShowingMovieList();
                do {
                    System.out.println("Enter id of the movie you want to book:");
                    try {
                        movieID = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.err.println("Input should be an integer!");
                        continue;
                    }
                    if (movieID < 1 || movieID > movieList.size()) {
                        System.err.println("ID out of range!");
                    } else {
                        moviegoerService.makeBookingByMovie(movieGoer,
                                movieList.get(movieID-1));
                        return 1;
                    }
                } while (true);
            }
        } while (true);
    }

    /**
     * Panel for user to view their order history and write comments
     * @param movieGoer user to view history and write comments
     * @return status, 1 success, 0 going back, -1 logout
     */
    public static int viewOrdersPanel(MovieGoer movieGoer) {
        if (moviegoerService.printAllOrders(movieGoer) == -1)
            return 1; // when there is no history
        int choice;
        do {
            System.out.println(" 1: Write review for the movie");
            System.out.println(" 0: Going back");
            System.out.println("-1: Logout");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Invalid choice!");
                continue;
            }
            if (choice < -1 || choice > 1) {
                System.err.println("Choice should be between -1 to 1");
            } else if (choice == -1 || choice == 0){
                return choice;
            } else {
                int orderID;
                do {
                    System.out.println("Enter the index of the order you want " +
                            "to review:");
                    try {
                        orderID = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.err.println("Input should be an integer!");
                        continue;
                    }
                    if (orderID < 1 || orderID > movieGoer.getPayments().size()) {
                        System.err.println("ID out of range!");
                    } else {
                        moviegoerService.writeReview(movieGoer,
                                movieGoer.getPayments().get(orderID-1)
                                        .getSession().getMovie());
                        return 1;
                    }
                } while (true);
            }
        } while (true);
    }

    /**
     * Panel for user to view reviews they have written
     * @param movieGoer writer of the reviews
     * @return status, 1 for success
     */
    public static int viewReviewPanel(MovieGoer movieGoer) {
        moviegoerService.listAllReviewsByMovieGoer(movieGoer);
        return 1;
    }
}