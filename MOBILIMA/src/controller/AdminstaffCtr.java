package controller;

import model.AdminStaff;
import model.Movie;

import java.util.Scanner;

public class AdminstaffCtr {
    public static AdminStaff AdminstaffLogin() {
        Scanner scan = new Scanner(System.in);
        String username;
        String password;
        while (true) {
            System.out.println("Please enter your username: ");
            username = scan.next();
            System.out.println("Please enter your password: ");
            password = scan.next();
            if (password.equals("123456") == true) break;
        }

        AdminStaff adminStaff = new AdminStaff(username, password);
        return adminStaff;
    }
}
