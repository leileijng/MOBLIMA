import Services.MoviegoerService;
import Services.Service;
import controller.*;
import model.*;
import java.util.Scanner;

public class Main {
    public static MovieGoer movieGoer;
    public static AdminStaff adminStaff;

    public static PriceTable priceTable;

    public static void main(String[] args) {
        Service.initialiseFromFile();
        systemInit();
    }

    public static void systemInit() {
        System.out.println("Please select mode:\n1. User Mode\t2.Admin Mode");

        Scanner scan = new Scanner(System.in);
        int sel = scan.nextInt();
        if (sel == 1) {
            movieGoer = MovieGoerCtr.movieGoerLogin();
            if (movieGoer!= null)
                MovieGoerCtr.printMainPanel(movieGoer);
        }
        else {
            adminStaff = AdminstaffCtr.AdminstaffLogin();
            AdminstaffCtr.printMainPanel(adminStaff);
        }
    }

    public static void movieGoerConsole() {

    }



}