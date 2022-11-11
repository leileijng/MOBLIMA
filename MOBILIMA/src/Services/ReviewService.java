package Services;

import model.Movie;
import model.MovieGoer;
import model.Review;

import java.util.List;
import java.util.Scanner;

public class ReviewService {
    private static final Scanner scanner = new Scanner(System.in);
    public static void addReview(MovieGoer movieGoer, Movie movie, double ratings, String comments) {
        // create review and append to relevant lists
        Review review = new Review(movieGoer.getUsername(), movie, ratings, comments);
        Service.reviews.add(review);
        movieGoer.addReview(review);
        movie.addReview(review);
    }


    /**
     * List all reviews wrote by a particular movie-goer
     * @param movieGoer writer of the reviews
     */
    public static void listAllReviewsByMovieGoer(MovieGoer movieGoer) {
        List<Review> reviews = movieGoer.getReviews();
        int i = 0;  // index
        if (reviews == null) {
            System.out.println("No records found!");
            return ;
        }
        for (Review review : reviews) {
            System.out.printf("=== Review %d ===\n", ++i);
            System.out.println("Movie Name: " + review.getMovie().getMovieTitle());
            System.out.println("Ratings:\t"+review.getRating());
            System.out.println("Comments:\t"+review.getComments());
        }
    }
}
