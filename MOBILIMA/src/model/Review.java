package model;
/**
 * Represents a review about a movie made by a moviegoer
 */
public class Review {
    /**
     * the username of review
     */
    private String username;
    /**
     * the movie of review
     */
    private final Movie movie;
    /**
     * the rating in a review
     */
    private final double rating;
    /**
     * the comments made by a moviegoer
     */
    private final String comments;


    /**
     * constructor of review
     * @param username username of the review's writer
     * @param movie movie to review
     * @param rating rating of the movie, an integer between 1 and 5
     * @param comments comments about the movie
     */
    public Review(String username, Movie movie, double rating, String comments) {
        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException("Rating should be between 1 and 5!");
        this.username = username;
        this.movie = movie;
        this.rating = rating;
        this.comments = comments;
    }

    /**
     * @return userID of the review's writer
     */
    public String getUsername() { return username; }

    /**
     * @return a pointer to the movie the review about
     */
    public Movie getMovie() { return movie; }
    /**
     * @return rating given in the review
     */
    public double getRating() { return rating; }
    /**
     * @return comments in the review
     */
    public String getComments() { return comments; }
}
