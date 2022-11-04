package controller;

import model.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.Vector;

public class SessionCtr {
    private static int countSession = 0;
    public static ArrayList<Session> sessions = new ArrayList<Session>();

    public static Scanner scan = new Scanner(System.in);

    public static Timestamp convertStr2Time(String time){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(time);
            java.sql.Timestamp timeStampDate = new Timestamp(parsedDate.getTime());
            return timeStampDate;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void addSessionToDB(Session session){
        sessions.add(session);
    }

    public static void addSession(){
        System.out.println("Please enter the movie title : ");
        String movieTitle = scan.next();
        System.out.println("Please enter the cinema code : ");
        String cinemaCode = scan.next();
        System.out.println("Please enter the start time : ");
        String startTime = scan.next();
        System.out.println("Please enter the end time : ");
        String endTime = scan.next();

        countSession += 1;
        String sessionIndex = "sessionIdx#" + countSession;

        Session session = new Session(sessionIndex,
                                    MovieCtr.getMovieByName(movieTitle),
                                    CinplexCtr.getCinplexById("G01").getCinema(cinemaCode),
                                    convertStr2Time(startTime),
                                    convertStr2Time(endTime)
                                    );
        sessions.add(session);
    }

    public static Session getSessionByIndex(){
        showSessions();
        Session session = null;
        do{
            System.out.println("Please enter the session index to edit : ");
            String sessionIdx = scan.next();
            session = getSessionBySessionIndex(sessionIdx);
            if(session == null) System.err.println("Please provide valid session ID!");
        }while(session == null);
        return session;
    }

    public static void editSession(){
        Session session = getSessionByIndex();
        System.out.println("Please enter the start time : ");
        String startTime = scan.next();
        System.out.println("Please enter the end time : ");
        String endTime = scan.next();

        session.setStartTime(convertStr2Time(startTime));
        session.setEndTime(convertStr2Time(endTime));
    }

    public static void removeSession(){
        System.out.println("Please enter the session index : ");
        String sessionIdx = scan.next();
        removeSession(sessionIdx);
    }

    public static void showSessions() {
        int num = sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            sessions.get(i).viewDetails();
        }
    }

    public void showSessionsAtCinema(String cinemaCode) {
        int num = this.sessions.size();
        for (int i = 0; i < num; i ++) {
            if (this.sessions.get(i).getCinema().getCinemaCode().equals(cinemaCode)) {//Compare string using == or !=
                this.sessions.get(i).viewDetails();
            }
        }
    }

    public static String removeSession(String sessionIndex) {
        int num = sessions.size();
        for (int i = 0; i < num; i ++) {
            if (sessions.get(i).getSessionIndex() == sessionIndex) {//incompatable type, string and int
                sessions.remove(i);
                return "Successfully Removing the Session.";
            }
        }
        return "Can't Find this Session Index.";
    }

    public static Session getSessionBySessionIndex(String sessionIndex) {
        int num = sessions.size();
        for (int i = 0; i < num; i ++) {
            if (sessions.get(i).getSessionIndex() == sessionIndex) {
                return sessions.get(i);
            }
        }
        return null;
    }

    public String addSession(Session session) {
        this.sessions.add(session);
        return "Successfully Adding the Session.";
    }
}
