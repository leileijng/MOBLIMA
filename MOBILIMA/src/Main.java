import controller.CinplexCtr;
import controller.MoviegoerCtr;
import model.AdminStaff;
import model.Cinplex;
import model.Movie;
import model.MovieGoer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Cinplex cinplex;
    public static ArrayList<Movie> movies;
    public static MovieGoer movieGoer;
    public static AdminStaff adminStaff;

    public static void main(String[] args) {
        initialiseData();


    }

    public static void initialiseData(){
        //an example of creating local objects and initialise data inside
        cinplex = new Cinplex("Golden Village", "Jurong Point");
        CinplexCtr.addCinema(cinplex);

    }

    public static void dataInport(){

    }
    public static void dataExport(){

    }

    public static void systemInit(){
        System.out.println("Please select mode:\n1. User Mode\t2.Admin Mode");

        Scanner scan = new Scanner(System.in);
        int sel = scan.nextInt();
        if(sel == 1) movieGoer = MoviegoerCtr.moviegoerLogin();
    }

    public static void movieGoerConsole(){}

    public static void adminConsole(){}

}