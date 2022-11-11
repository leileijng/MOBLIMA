import Services.MoviegoerService;
import Services.Service;
import controller.*;
import model.*;
import java.util.Scanner;

public class Main {
    public static MovieGoer movieGoer;
    public static AdminStaff adminStaff;

    private static void clear() {
        System.out.print("\033[H\033[2J");
    }

    public static void main(String[] args) {
        Service.initialiseFromFile();
        systemInit();
        System.out.println("\nThank you for using MOBLIMA!");
    }

    public static void systemInit() {
        Scanner scan = new Scanner(System.in);
        int sel;
        do {
            clear();
            System.out.print("""
                              .----.
                  .---------. | == |
                  |.-""\"""-.| |----|
                  ||       || | == |
                  ||       || |----|
                  |'-.....-'| |::::|
                  `"")---(""` |___.|
                 /:::::::::::\\" _  "
                /:::=======:::\\`\\`\\
                `""\"""\"""\"""\""`  '-'""".indent(4));
            System.out.println("**************************\n" +
                    "*   Welcome to MOBLIMA   * \n" +
                    "*     Golden Village     *\n**************************\n");
            System.out.println("Please select mode:\n " +
                    "1. User Mode\t 2.Admin Mode\t-1. Quit");
            try {
               sel = Integer.parseInt(scan.nextLine());
           } catch (Exception e) {
               System.err.println("Input should be an integer!");
               continue;
           }
           if (sel < -1 || sel > 2) {
               System.err.println("select out of range!");
           } else {
               switch (sel) {
                   case 1 -> {
                       movieGoer = MovieGoerCtr.movieGoerLogin();
                       if (movieGoer!= null)
                           MovieGoerCtr.printMainPanel(movieGoer);
                   }
                   case 2 -> {
                       adminStaff = AdminstaffCtr.AdminstaffLogin();
                       AdminstaffCtr.printMainPanel(adminStaff);
                   }
                   case -1 -> { return ;}
               }
           }
        } while (true);
    }



}