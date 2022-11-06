package Services;

import model.*;

import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MoviegoerService extends Service{
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
                }
            }
            System.out.println("Enter your choice: ");
            select = Integer.parseInt(scanner.nextLine());
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
                case 2 -> MovieService.printTop5MovieByRevenue();
                case 3 -> MovieService.printTop5MovieByRatings();
                default -> {
                    System.out.println("Invalid choice!");
                }
            }
            System.out.println("1: View All Current Movies");
            if (canListByRevenue)
                System.out.println("2: View Top 5 Movie by Revenue");
            if (canListByRatings)
                System.out.println("3: View Top 5 Movie by Ratings");
            System.out.println("0: Go Back");
            System.out.println("-1: Logout");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
        }
    }

    /**
     * User can check all current movies and make bookings.
     * @param movieGoer movieGoer to view all Movies
     * @return user choice, 1 for successfully made booking, -1 for failed to
     * make payment, 0 for going back to
     */
    public static int viewAllMovies(MovieGoer movieGoer) {
        List<Movie> movies = MovieService.getShowingMovieList();
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
                }
            }
            System.out.println("Choice 1: Make bookings");
            System.out.println("Choice 0: Going back");
            choice = Integer.parseInt(scanner.nextLine());
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
        //SessionCtr.showSessions();
        System.out.println("Enter session ID: ");
        // TODO change session prompt message for user
        Session session = SessionService.getSessionByIndex();
        System.out.println("Layout: ");
        session.viewTickets();

        System.out.println("Select row of the seat: ");
        String row = scanner.nextLine();
        System.out.println("Select col of the seat: ");
        String col = scanner.nextLine();
        String seatID = row + col;

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
            choice = Integer.parseInt(scanner.nextLine());
        }
        double price = 0;
        switch (choice) {
            case 1 -> price = adultPrice;
            case 2 -> price = seniorPrice;
            case 3 -> price = studentPrice;
        }
        session.occupySeat(seatID, price);
        Payment payment = new Payment(generateTransactionID(session), session,
                movieGoer, price);
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
    public static double calculateAdultPrice(Session session, String seatID) {
        String movieType = session.getMovie().getMovieType().toString();
        String cinemaClass = session.getCinema().getClassOfCinema().toString();
        int seatType = session.getTicketBySeatID(seatID).getSeatType();
        return new AdultPrice(movieType, cinemaClass, seatType).calculatePrice();
    }

    public static double calculateSeniorPrice(Session session, String seatID) {
        String movieType = session.getMovie().getMovieType().toString();
        String cinemaClass = session.getCinema().getClassOfCinema().toString();
        int seatType = session.getTicketBySeatID(seatID).getSeatType();
        return new SeniorPrice(movieType, cinemaClass, seatType).calculatePrice();
    }

    public static double calculateStudentPrice(Session session, String seatID) {
        String movieType = session.getMovie().getMovieType().toString();
        String cinemaClass = session.getCinema().getClassOfCinema().toString();
        int seatType = session.getTicketBySeatID(seatID).getSeatType();
        return new StudentPrice(movieType, cinemaClass, seatType).calculatePrice();
    }

    public static void listAllReviews(MovieGoer movieGoer) {
        List<Review> reviews = movieGoer.getReviews();
        for (Review review : reviews) {
            System.out.println("Ratings:\t"+review.getRating());
            System.out.println("Comments:\t"+review.getComments());
        }
    }

    public static String generateTransactionID(Session session) {
        // XXXYYYYMMDDhhmm
        // (Y : year, M : month, D : day, h : hour, m :minutes, XXX : cinema code in letters)
        String cinemaCode = session.getCinema().getCinemaCode();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime time = LocalDateTime.now();
        return cinemaCode.substring(0, 3) + dtf.format(time);
    }

    public static void capturePersonalInfo(MovieGoer movieGoer) {
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
        System.out.println(" 1: Add Review");
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
                Movie movie = payments.get(recordID).getSession().getMovie();
                Review review = new Review(movieGoer.getUsername(), movie,
                        ratings, comments);
                movie.addReview(review);
            }
            System.out.println("1: Add Review");
            System.out.println("-1: quit");
            System.out.println("Enter your choice");
            choice = Integer.parseInt(scanner.nextLine());
        }
    }
}

