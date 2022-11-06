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
        int sel = -1;
        do{

            //int sel = -1;
            //select mode
            mgCtr.mainPanel(movieGoer);
        }while(sel != -1);
        systemInit();
    }


    public static void dataInport() {

    }

    public static void dataExport() {

    }

    public static void systemInit() {
        System.out.println("Please select mode:\n1. User Mode\t2.Admin Mode");

        Scanner scan = new Scanner(System.in);
        int sel = scan.nextInt();
        if (sel == 1) {
            movieGoer = MoviegoerService.moviegoerLogin();
            MoviegoerService.printMainPanel(movieGoer);
        }
        else {
            AdminstaffCtr adminCtr = new AdminstaffCtr();
            adminStaff = adminCtr.AdminstaffLogin();
            adminCtr.printMainPanel(adminStaff);
        }
    }

    public static void movieGoerConsole() {

    }



}