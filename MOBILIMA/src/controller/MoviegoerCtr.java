package controller;

import model.MovieGoer;

import java.util.Scanner;

public class MoviegoerCtr {
    public static MovieGoer moviegoerLogin(){
        System.out.println("Please enter your username: ");
        Scanner scan = new Scanner(System.in);
        String userName = scan.next();

        MovieGoer movieGoer = new MovieGoer(userName);
        return movieGoer;
    }

    public void makeReview(){}

    public void makePayment(){}

}
