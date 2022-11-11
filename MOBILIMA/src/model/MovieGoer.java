package model;

import java.util.*;
/**
 * Represents Moviegoer who has made transaction in the system
 */
public class MovieGoer extends User {
    /**
     * age group of Moviegoer
     */
    private String ageGroup;
    /**
     * payment list the moviegoer has
     */
    private ArrayList<Payment> payments;
    /**
     * review list the moviegoer has
     */
    private ArrayList<Review> reviews;

    /**
     * constructor of new moviegoer
     * @param userName
     */
    public MovieGoer(String userName){
        super(userName);
        this.payments = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    /**
     * return list of payment the moviegoer has
     * @return
     */
    public ArrayList<Payment> getPayments() {
        return payments;
    }

    /**
     * return list of review the moviegoer has
     * @return
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * add payments
     * @param payment
     */
    public void addPayments(Payment payment){
        this.payments.add(payment);
    }

    /**
     * add reviews
     * @param review
     */
    public void addReview(Review review){
        this.reviews.add(review);
    }
}