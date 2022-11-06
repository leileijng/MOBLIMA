package model;

public class Review {
    private final String userID;
    private final Movie movie;
    private final double rating;
    private final String comments;


    /**
     * @param userID userID of the review's writer
     * @param movie movie to review
     * @param rating rating of the movie, an integer between 1 and 5
     * @param comments comments about the movie
     */

    public Review(String userID, Movie movie, double rating, String comments) {
        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException("Rating should be between 1 and 5!");
        this.userID = userID;
        this.movie = movie;
        this.rating = rating;
        this.comments = comments;
    }

    /**
     * @return userID of the review's writer
     */
    public String getUserID() { return userID; }

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

    public static void main(String[] args) {
        Movie jvpr = new Movie("JVPR");
        Review r1 = new Review("123", jvpr, 5, "Best movie ever!(ɔ◔‿◔)ɔ ♥");
        jvpr.addReview(r1);
        System.out.println(r1.getUserID());
        System.out.println(r1.getMovie().getMovieTitle());
        System.out.println(r1.getRating());
        System.out.println(r1.getComments());
        Review r2 = new Review("456", jvpr, 4, "Good movie!");
        jvpr.addReview(r2);
        Review r3 = new Review("789", jvpr, 3, "Average movie...");
        jvpr.addReview(r3);
        System.out.println(jvpr.getOverallRating());
        try {
            Review r4 = new Review("123", jvpr, 6, "Best best movie!");
            jvpr.addReview(r4);
        } catch (IllegalArgumentException e) {
            System.out.println("rating 6 is out of range!");
        }
    }
}
