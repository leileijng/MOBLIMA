package controller;

import service.AdminService;
import service.MovieService;
import model.AdminStaff;

import java.util.Scanner;
/**
 * Controller for Admin staff, providing interaction with the admin user
 */
public class AdminstaffCtr {
    /**
     * static admin service
     */
    public static AdminService adminService = new AdminService();

    /**
     * fixed admin staff
     */
    public static AdminStaff adminStaff = new AdminStaff("admin", "123456");

    /**
     * admin staff login
     * @return An AdminStaff object
     */
    public static AdminStaff AdminstaffLogin() {
        Scanner scan = new Scanner(System.in);
        String username;
        String password;
        do {
            System.out.println("Please enter your username: ");
            username = scan.nextLine();
            System.out.println("Please enter your password: ");
            password = scan.nextLine();
            if (username.equals(adminStaff.getUserName()) && password.equals(adminStaff.getAccessCode()) == true) break;
        } while(true);

        return new AdminStaff(username, password);
    }

    /**
     * admin staff main panel
     * @param adminStaff user of the panel
     */
    public static void printMainPanel(AdminStaff adminStaff){
        Scanner scan = new Scanner(System.in);
        int op = 0;
        boolean format = false;
        do {
            try {
                System.out.println("\n=== Main Panel ===");
                System.out.println("Please enter your option of functions: ");
                System.out.println("1. view movies");
                System.out.println("2. manage movies");
                System.out.println("3. view sessions");
                System.out.println("4. manage sessions");
                System.out.println("5. system configuration");
                System.out.println("6. log out");

                String op_s = scan.nextLine();
                op = Integer.parseInt(op_s);
            }
            catch(Exception e) {
                System.err.println("Please enter valid selection!");
            }
            if (op > 6 || op < 1) {
                System.err.println("Please enter within the selection range (1-6)!");
            }
            else{
                if (op == 1) {
                    do {
                        int op2 = 0;
                        try {
                            System.out.println("\n=== Movie Panel ===");
                            System.out.println("Please enter your option of functions: ");
                            System.out.println("1. view all movies");
                            System.out.println("2. view top 5 movies by revenue");
                            System.out.println("3. view top 5 movies by ratings");
                            System.out.println("4. go back");
                            System.out.println("5. log out");

                            String op2_s = scan.nextLine();
                            op2 = Integer.parseInt(op2_s);
                        }
                        catch(Exception e) {
                            System.err.println("Please enter valid selection!");
                        }
                        if (op2 > 5 || op2 < 1) {
                            System.err.println("Please enter within the selection range (1-5)!");
                        }
                        else {
                            if (op2 == 1) MovieService.printAllMovies();
                            else if (op2 == 2) MovieService.printTop5MovieByRevenue();
                            else if (op2 == 3) MovieService.printTop5MovieByRatings();
                            else if (op2 == 4) {
                                System.out.println("Back to Admin Console.");
                                break;
                            }
                            else if (op2 == 5) {
                                System.out.println("System exits!");
                                return;
                            }
                        }
                    } while (true);
                }
                else if (op == 2){
                    do {
                        int op2 = 0;
                        try {
                            System.out.println("\n=== Main Panel ===");
                            System.out.println("Please enter your option of functions: ");
                            System.out.println("1. add a new movie");
                            System.out.println("2. update movie details");
                            System.out.println("3. remove a existing movie");
                            System.out.println("4. go back");
                            System.out.println("5. log out");

                            String op2_s = scan.nextLine();
                            op2 = Integer.parseInt(op2_s);
                        }
                        catch(Exception e) {
                            System.err.println("Please enter valid selection!");
                        }
                        if (op2 > 5 || op2 < 1) {
                            System.err.println("Please enter within the selection range (1-5)!");
                        }
                        else {
                            if (op2 == 1) adminService.addNewMovie();
                            else if (op2 == 2) adminService.updateMovieDetails();
                            else if (op2 == 3) adminService.unlistMovie();
                            else if (op2 == 4) {
                                System.out.println("Back to Admin Console.");
                                break;
                            }
                            else if (op2 == 5) {
                                System.out.println("System exits!");
                                return;
                            }
                        }
                    } while (true);
                }
                else if (op == 3){
                    do {
                        int op2 = 0;
                        try {
                            System.out.println("\n=== Session Panel ===");
                            System.out.println("Please enter your option of functions: ");
                            System.out.println("1. view all sessions");
                            System.out.println("2. view sessions by cinplex name");
                            System.out.println("3. view sessions by movie title");
                            System.out.println("4. go back");
                            System.out.println("5. log out");

                            String op2_s = scan.nextLine();
                            op2 = Integer.parseInt(op2_s);
                        }
                        catch(Exception e) {
                            System.err.println("Please enter valid selection!");
                        }
                        if (op2 > 5 || op2 < 1) {
                            System.err.println("Please enter within the selection range (1-5)!");
                        }
                        else {
                            if (op2 == 1) adminService.viewAllSessions();
                            else if (op2 == 2){
                                System.out.println("Please enter the cinplex name: ");
                                String cinplexName = scan.nextLine();
                                adminService.viewAllSessionsByCineplexName(cinplexName);
                            }
                            else if (op2 == 3){
                                System.out.println("Please enter the movie title: ");
                                String movieTitle = scan.nextLine();
                                adminService.viewAllSessionsByMovieTitle(movieTitle);
                            }
                            else if (op2 == 4) {
                                System.out.println("Back to Admin Console.");
                                break;
                            }
                            else if (op2 == 5) {
                                System.out.println("System exits!");
                                return;
                            }
                        }
                    } while (true);
                }
                else if (op == 4){
                    do {
                        int op2 = 0;
                        try {
                            System.out.println("\n=== Session Panel ===");
                            System.out.println("Please enter your option of functions: ");
                            System.out.println("1. add a new session");
                            System.out.println("2. update session details");
                            System.out.println("3. remove a existing session");
                            System.out.println("4. go back");
                            System.out.println("5. log out");

                            String op2_s = scan.nextLine();
                            op2 = Integer.parseInt(op2_s);
                        }
                        catch(Exception e) {
                            System.err.println("Please enter valid selection!");
                        }
                        if (op2 > 5 || op2 < 1) {
                            System.err.println("Please enter within the selection range (1-5)!");
                        }
                        else {
                            if (op2 == 1) adminService.addNewSession();
                            else if (op2 == 2) adminService.updateSession();
                            else if (op2 == 3) adminService.delSession();
                            else if (op2 == 4) {
                                System.out.println("Back to Admin Console.");
                                break;
                            }
                            else if (op2 == 5) {
                                System.out.println("System exits!");
                                return;
                            }
                        }
                    } while (true);
                }
                else if (op == 5){
                    do {
                        int op2 = 0;
                        try {
                            System.out.println("\n=== Config Panel ===");
                            System.out.println("Please enter your option of functions: ");
                            System.out.println("1. update price table");
                            System.out.println("2. update holiday set");
                            System.out.println("3. update the movie filter");
                            System.out.println("4. go back");
                            System.out.println("5. log out");

                            String op2_s = scan.nextLine();
                            op2 = Integer.parseInt(op2_s);
                        }
                        catch(Exception e) {
                            System.err.println("Please enter valid selection!");
                        }
                        if (op2 > 5 || op2 < 1) {
                            System.err.println("Please enter within the selection range (1-5)!");
                        }
                        else {
                            if (op2 == 1) 
                                adminService.updatePriceTable();
                            else if (op2 == 2) adminService.updatePublicHoliday();
                            else if (op2 == 3) adminService.settingFilter();
                            else if (op2 == 4) {
                                System.out.println("Back to Admin Console.");
                                break;
                            }
                            else if (op2 == 5) {
                                System.out.println("System exits!");
                                return;
                            }
                        }
                    } while (true);
                }
                else if (op == 6) {
                    System.out.println("System exits!");
                    break;
                }
            }
        } while (true);
    }
}
