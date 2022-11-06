package controller;

import Services.AdminService;
import Services.SessionService;
import model.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.Vector;

public class SessionCtr {

    public static void addSessionToDB(Session session) {
        SessionService.addSessionToDB(session);
    }

    public static void addSession(){
        SessionService.addSession();
    }

    public static void editSession(){
        SessionService.editSession();
    }

    public static void removeSession(){
        SessionService.removeSession();
    }

}
