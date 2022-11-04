package model;
import controller.MoviegoerCtr;

import java.io.*;
import java.util.*;

public class MovieGoer extends User {
    private String ageGroup;
    private ArrayList<Payment> payments;
    private ArrayList<Review> reviews;
    private User user;

    public MovieGoer(String userName){
        super(userName);
    }
    public MovieGoer(String ageGroup, ArrayList<Payment> payments, ArrayList<Review> reviews, User user, String userName, String phone, String email, int age) {
        super(userName, phone, email, age);
        this.ageGroup = ageGroup;
        this.payments = payments;
        this.reviews = reviews;
        this.user = user;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void addPayments(Payment payment){
        this.payments.add(payment);
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }
}