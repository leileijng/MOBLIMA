package Services;

import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PaymentService {
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Make booking for movieGoer for movie
     * @param movieGoer user to book
     * @param movie movie to be booked
     * @return status of booking, 1 for success, 0 for fail
     */
    public static int makeBookingByMovie(MovieGoer movieGoer, Movie movie) {
        // list sessions for user to select for the movie
        System.out.println("Booking for movie " + movie.getMovieTitle());
        SessionService.showSessionsByMovie(movie);

        // let user select session
        Session session = SessionService.getSessionByIndex(
                "Please enter the session index to book:");

        // shows available seats and let user select seat
        System.out.println("Layout: ");
        session.viewTickets();
        Ticket ticket = null;
        String seatID = "";
        // check for seat availability
        do{
            System.out.println("Select row of the seat: ");
            String row = scanner.nextLine();
            System.out.println("Select col of the seat: ");
            String col = scanner.nextLine();
            seatID = row + col;
            ticket = session.getTicketBySeatID(seatID);
            if(ticket == null) System.err.println("There is no seat in your selected place");
            else if(ticket.isBooked()) {
                ticket = null;
                System.err.println("The seat is not available");
            }
        }while(ticket == null);

        // calculate prices for adult, senior, and student
        // and let users choose which ticket to buy
        Price price_obj = new AdultPrice(session.getTicketBySeatID(seatID),session);
        double adultPrice = price_obj.calculatePrice();

        price_obj = new SeniorPrice(session.getTicketBySeatID(seatID),session);
        double seniorPrice = price_obj.calculatePrice();

        price_obj = new StudentPrice(session.getTicketBySeatID(seatID),session);
        double studentPrice = price_obj.calculatePrice();

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
        Service.payments.add(payment);
        movieGoer.addPayments(payment);
        // add revenue for the movie
        movie.addRevenue(price);
        // capture personal information after booking
        capturePersonalInfo(movieGoer);

        System.out.println("Booking has been made!");
        return 1;
    }

    /**
     * Helper function to generate transaction ID based on session and time
     * in the format XXXYYYYMMDDhhmm where Y : year, M : month, D : day,
     * h : hour, m :minutes, XXX : cinema code in letters
     * @param session session the user is to book
     * @return transaction ID
     */
    private static String generateTransactionID(Session session) {
        String cinemaCode = session.getCinema().getCinemaCode();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime time = LocalDateTime.now();
        return cinemaCode + dtf.format(time);
    }

    /**
     * Helper function to capture personal information of the user after booking
     * @param movieGoer movie-goer made the booking
     */
    private static void capturePersonalInfo(MovieGoer movieGoer) {
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
}
