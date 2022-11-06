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
        initialiseData();
        systemInit();
    }

    public static void initialiseData() {
        LayoutCtr.importLayout();
        CinplexCtr.loadCinplexFromFile();
        CinplexCtr.loadCinemaFromFile();

        //CinplexCtr.getCinplexById("ciine_1").viewCinemas();

        MovieCtr.addMovieToDB(new Movie("Her"));
        MovieCtr.addMovieToDB(new Movie("Spider Man"));
        MovieCtr.addMovieToDB(new Movie("Hello World"));

        SessionCtr.addSessionToDB(
                new Session( "Her_s1",
                        MovieCtr.getMovieByName("Her"),
                        CinplexCtr.getCinplexById("ciine_1").getCinema("N01"),
                        SessionCtr.convertStr2Time("2022-11-19 15:00:00"),
                        SessionCtr.convertStr2Time("2022-11-19 17:00:00")
                ));

        SessionCtr.addSessionToDB(
                new Session( "Her_s2",
                        MovieCtr.getMovieByName("Spider Man"),
                        CinplexCtr.getCinplexById("ciine_1").getCinema("G02"),
                        SessionCtr.convertStr2Time("2022-11-20 15:00:00"),
                        SessionCtr.convertStr2Time("2022-11-20 17:00:00")
                ));
        // new priceTable
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
            movieGoer = MoviegoerCtr.moviegoerLogin();
            MoviegoerCtr.printMainPanel(movieGoer);
        }
        else {
            adminStaff = AdminstaffCtr.AdminstaffLogin();
            AdminstaffCtr.printMainPanel(adminStaff);
        }
    }

    public static void movieGoerConsole() {

    }



}