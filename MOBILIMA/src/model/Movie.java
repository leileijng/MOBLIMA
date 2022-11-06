package model;

import java.util.*;

/**
 * Represents a movie in the movie database MovieDB.
 * A movie can be changed its showing status, update revenue, and add reviews.
 */
public class Movie {
    /**
     * The title of the movie.
     */
    private String movieTitle;
    /**
     * The showing status of the movie.
     */
    private ShowingStatus showingStatus;

    private String movieType;
    /**
     * An abstract of the movie.
     */
    private String synopsis;
    /**
     * Director of the movie.
     */
    private String director;
    /**
     * Casts of a movie, at least two.
     */
    private String[] casts;
    /**
     * Overall rating of the movie,
     * calculated by the average of ratings in the review
     * and will be updated every time a new review added.
     */
    private double overallRating;
    /**
     * A list of all reviews about the movie.
     */
    private List<Review> reviewList;
    /**
     * Total revenue of the movie in SGD.
     */
    private double totalRevenue;

    private Date dateEndOfShowing;

    public Movie() {
        reviewList = new ArrayList<Review>();
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    /**
     * Constructor of the movie, set movie title and
     * initialize reviewList and totalRevenue,
     * but other attributes needs to be set.
     * @param movieTitle title of the movie.
     */
    public Movie(String movieTitle) {
        this.movieTitle = movieTitle;
        this.showingStatus = ShowingStatus.NOWSHOWING;
        reviewList = new LinkedList<>();
        this.overallRating = 0;
    }

    /**
     * Constructor of the movie, set all attributes and
     * initialize reviewList and totalRevenue.
     * @param movieTitle title of the movie.
     * @param showingStatus current showing status.
     * @param synopsis a short introduction of the movie.
     * @param director director of the movie.
     * @param casts casts of the movie(at least 2).
     */
    public Movie(String movieTitle, ShowingStatus showingStatus,
                 String synopsis, String director, String[] casts) {
        this.movieTitle = movieTitle;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.director = director;
        this.casts = casts;
        this.overallRating = 0;
        this.totalRevenue = 0;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }


    /**
     * Gets the title of the movie.
     * @return title of the movie.
     */

    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Gets the showing status of the movie.
     * @return current showing status of the movie.
     */
    public ShowingStatus getShowingStatus() {
        // return showingStatus enum or return a text string?
        return showingStatus;
    }

    /**
     * Gets an abstract of the movie.
     * @return an abstract about the movie.
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Gets overall rating of the movie to one decimal.
     * @return average rating of the movie's review to one decimal point.
     */
    public double getOverallRating() {
        if (reviewList.size() <= 1)
            return -1;
        return Math.round(overallRating * 10) / 10.0;
    }

    /**
     * Gets total revenue of the movie in SGD.
     * @return total revenue of the movie to two decimal points (SGD).
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Gets the director of the movie.
     * @return director of the movie.
     */
    public String getDirector() {
        return director;
    }

    public Date getDateEndOfShowing() {
        return dateEndOfShowing;
    };

    /**
     * Gets the casts of the movie.
     * @return casts of the movie in a String array.
     */
    public String[] getCasts() {
        return casts;
    }

    /**
     * Changes the showing status of the movie.
     * @param showingStatus new showing status for the movie.
     */
    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }

    /**
     * Change the abstract of the movie.
     * @param synopsis new abstract about the movie
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Change the director of the movie.
     * @param director new director of the movie
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Change the casts of the movie.
     * @param casts casts of the movie at least two in String Array.
     */
    public void setCasts(String[] casts) {
        this.casts = casts;
    }

    /**
     * Add a review to the review list of the movie and
     * calculate the updated overall rating of the movie.
     * @param review new review about the movie
     */
    public void addReview(Review review) {
        if (this != review.getMovie())
            throw new IllegalArgumentException("Movie titles don't match!");
        this.overallRating = (this.overallRating * this.reviewList.size() +
                review.getRating()) / (this.reviewList.size()+1);
        reviewList.add(review);
    }

    public void setDateEndOfShowing(Date date) {
        this.dateEndOfShowing = date;
    }

    /**
     * Print info of the movie to the terminal.
     * (or return a string of information?)
     */
    public void printInfo() {
        StringBuilder info = new StringBuilder("***\n");
        info.append("Title: \t").append(getMovieTitle()).append("\n");
        info.append("Synopsis: \t").append(getSynopsis()).append("\n");
        info.append("Director: \t").append(getDirector()).append("\n");
        info.append("Casts: \t").append(Arrays.toString(getCasts()))
                .append("\n");
        info.append("Rating: \t").append(getOverallRating()).append("\n");
        info.append("Revenue: \t").append(getTotalRevenue()).append("\n");
        System.out.println(info.toString());
    }
    /**
     * Adds revenue of the movie
     * @param revenue newly created revenue by the movie (in SGD)
     */
    public void addRevenue(double revenue) {
        this.totalRevenue += revenue;
    }

    public void printRecentReviews() {
        for (int i = 0; i < Math.min(5, reviewList.size()); ++i) {
            Review review = reviewList.get(i);
            System.out.printf("Rating: %d\n", review.getRating());
            System.out.printf("Comments: %s\n", review.getComments());
        }
    }

    public static void main(String[] args) {
        // test constructors, setters, getters
        Movie jvpr = new Movie("JVPR");
        jvpr.setSynopsis("A movie about JVPR conference.(ง︡'-'︠)ง");
        jvpr.setDirector("Someone");
        jvpr.setCasts(new String[] {"some one", "some two"});
        System.out.println(jvpr.getDirector());
        System.out.println(jvpr.getMovieTitle());
        System.out.println(jvpr.getSynopsis());
        System.out.println(Arrays.toString(jvpr.getCasts()));
        jvpr.printInfo();

        // test addReview()
        Review r1 = new Review
                ("123", jvpr, 5, "Best Movie Ever!");
        jvpr.addReview(r1);
        Review r2 = new Review
                ("456", jvpr, 4, "Good movie!");
        jvpr.addReview(r2);
        Review r3 = new Review
                ("789", jvpr, 4, "Average movie...");
        jvpr.addReview(r3);
        System.out.println(jvpr.getOverallRating());
         try {
            Movie ntu = new Movie("NTU");
            Review r4 = new Review
                    ("567", ntu, 5, "Best Movie Ever!");
            jvpr.addReview(r4);
         } catch (IllegalArgumentException e) {
             System.out.println("Titles not match!");
         }

         // test addRevenue()
         System.out.println(jvpr.getTotalRevenue());
         jvpr.addRevenue(500);
         jvpr.addRevenue(3.14);
         System.out.println(jvpr.getTotalRevenue());
    }

    public void setDateEndOfShowingDate(int year, int month, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month, day);
        dateEndOfShowing = calendar.getTime();
    }

}