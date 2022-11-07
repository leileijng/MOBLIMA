package Services;

import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MoviegoerService extends Service{
    private static final Scanner scanner = new Scanner(System.in);
    private boolean canListByRevenue;
    private boolean canListByRatings;
    // list of all payments history
    private final List<Payment> payments = new LinkedList<>();
    // list of all reviews
    private final List<Review> reviews = new LinkedList<>();

    /**
     * Update movie-goers accessibility to view movies
     * sorted by revenue or by ratings.
     */
    public void updateSortingStatus() {
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
    public void updateListByRevenue() {
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
    public void updateListByRatings() {
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
     * Make booking for movieGoer for movie
     * @param movieGoer user to book
     * @param movie movie to be booked
     * @return status of booking, 1 for success, 0 for fail
     */
    public int makeBookingByMovie(MovieGoer movieGoer, Movie movie) {
        // list sessions for user to select for the movie
         SessionService.showSessionsByMovie(movie);

        // let user select session
        System.out.println("Enter session ID: ");
        // TODO SessionService change session prompt message for user
        Session session = SessionService.getSessionByIndex();

        // shows available seats and let user select seat
        System.out.println("Layout: ");
        session.viewTickets();
        System.out.println("Select row of the seat: ");
        String row = scanner.nextLine();
        System.out.println("Select col of the seat: ");
        String col = scanner.nextLine();
        String seatID = row + col;

        // calculate prices for adult, senior, and student
        // and let users choose which ticket to buy
        double adultPrice = calculateAdultPrice(session, seatID);
        double seniorPrice = calculateSeniorPrice(session, seatID);
        double studentPrice = calculateStudentPrice(session, seatID);
        System.out.println("Select type of price: ");
        System.out.printf("1: Adult Price %.2f\n", adultPrice);
        System.out.printf("2: Senior Price %.2f\n", seniorPrice);
        System.out.printf("3: Student Price %.2f\n", studentPrice);
        int choice = Integer.parseInt(scanner.nextLine());
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice! Enter again: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Choice should be between 1 and 3!");
            }
        } //  verify user input
        double price = 0;
        switch (choice) {
            case 1 -> price = adultPrice;
            case 2 -> price = seniorPrice;
            case 3 -> price = studentPrice;
        }

        // assign price to ticket and generate payment record
        session.occupySeat(seatID, price);
        Payment payment = new Payment(generateTransactionID(session), session,
                movieGoer, price);

        // add payment history to relevant lists to store
        payments.add(payment);
        movieGoer.addPayments(payment);
        // add revenue for the movie
        movie.addRevenue(price);
        // capture personal information after booking
        capturePersonalInfo(movieGoer);

        System.out.println("Booking has been made!");
        return 1;
    }

    /**
     * Helper function to calculate adult price for a particular session and seat,
     * based on the price table.
     * @param session session the booking is to book
     * @param seatID seat the user is to book
     * @return calculated price
     */
    public static double calculateAdultPrice(Session session, String seatID) {
        String movieType = session.getMovie().getMovieType().toString();
        String cinemaClass = session.getCinema().getClassOfCinema().toString();
        int seatType = session.getTicketBySeatID(seatID).getSeatType();
        return new AdultPrice(movieType, cinemaClass, seatType).calculatePrice();
    }

    /**
     * Helper function to calculate senior price for a particular session and seat,
     * based on the price table.
     * @param session session the booking is to book
     * @param seatID seat the user is to book
     * @return calculated price
     */
    public double calculateSeniorPrice(Session session, String seatID) {
        String movieType = session.getMovie().getMovieType().toString();
        String cinemaClass = session.getCinema().getClassOfCinema().toString();
        int seatType = session.getTicketBySeatID(seatID).getSeatType();
        return new SeniorPrice(movieType, cinemaClass, seatType).calculatePrice();
    }

    /**
     * Helper function to calculate student price for a particular session and seat,
     * based on the price table.
     * @param session session the booking is to book
     * @param seatID seat the user is to book
     * @return calculated price
     */
    public double calculateStudentPrice(Session session, String seatID) {
        String movieType = session.getMovie().getMovieType().toString();
        String cinemaClass = session.getCinema().getClassOfCinema().toString();
        int seatType = session.getTicketBySeatID(seatID).getSeatType();
        return new StudentPrice(movieType, cinemaClass, seatType).calculatePrice();
    }

    /**
     * Helper function to generate transaction ID based on session and time
     * in the format XXXYYYYMMDDhhmm where Y : year, M : month, D : day,
     * h : hour, m :minutes, XXX : cinema code in letters
     * @param session session the user is to book
     * @return transaction ID
     */
    public String generateTransactionID(Session session) {
        String cinemaCode = session.getCinema().getCinemaCode();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime time = LocalDateTime.now();
        return cinemaCode.substring(0, 3) + dtf.format(time);
    }

    /**
     * List all reviews wrote by a particular movie-goer
     * @param movieGoer writer of the reviews
     */
    public void listAllReviewsByMovieGoer(MovieGoer movieGoer) {
        List<Review> reviews = movieGoer.getReviews();
        int i = 0;  // index
        if (reviews == null) {
            System.out.println("No records found!");
            return ;
        }
        for (Review review : reviews) {
            System.out.printf("=== Review %d ===\n", ++i);
            System.out.println("Ratings:\t"+review.getRating());
            System.out.println("Comments:\t"+review.getComments());
        }
    }



    /**
     * Helper function to capture personal information of the user after booking
     * @param movieGoer movie-goer made the booking
     */
    public void capturePersonalInfo(MovieGoer movieGoer) {
        System.out.println("Please input your name: ");
        String name = scanner.nextLine();
        System.out.println("Please input your mobile number: ");
        String mobileNo = scanner.nextLine();
        System.out.println("Please input your email: ");
        String email = scanner.nextLine();
        System.out.println("Please input your age: ");
        int age = Integer.parseInt(scanner.nextLine());
        movieGoer.setUsername(name);
        movieGoer.setPhone(mobileNo);
        movieGoer.setEmail(email);
        movieGoer.setAge(age);
    }

    /**
     * Print all booking history of a movie-goer
     * @param movieGoer movie-goer to print history
     */
    public int printAllOrders(MovieGoer movieGoer) {
        List<Payment> payments = movieGoer.getPayments();
        int i = 0;
        if (payments == null) {
            System.out.println("No history found!");
            return -1;
        }
        for (Payment payment : payments) {
            // TODO: Add ticket information
            // Ticket ticket = payment.getTicket();
            System.out.printf("=== For record %d ===\n", ++i);
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
        int ratings = -1;
        do {
            System.out.println("Enter the rating of the movie: " +
                    "(integer between 1 to 5");
            try {
                ratings = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Input should be an integer!");
                continue;
            }
            if (ratings < 1 || ratings > 5)
                System.err.println("Input should be between 1 to 5!");
            else
                break;
        } while (true); // get ratings from the user
        System.out.println("Enter your comments: ");
        String comments = scanner.nextLine(); // get comments from the user

        // create review and append to relevant lists
        Review review = new Review(movieGoer.getUsername(), movie, ratings, comments);
        reviews.add(review);
        movieGoer.addReview(review);
        movie.addReview(review);
    }

}

