package controller;

import model.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.sql.Timestamp;

public class SessionCtr {
    private static int countSession = 0;
    private static Timetable timetable;

    public static Timestamp convertStr2Time(String time){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(time);
            java.sql.Timestamp timeStampDate = new Timestamp(parsedDate.getTime());
            return timeStampDate;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void addSession(){
        Scanner scan = new Scanner(System.in);
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
                                    MovieCtr.getMovie(movieTitle),
                                    CinplexCtr.getCinema(cinemaCode),
                                    convertStr2Time(startTime),
                                    convertStr2Time(endTime)
                                    );
        timetable.addSession(session);
    }

    public static void editSession(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the session index : ");
        String sessionIdx = scan.next();
        System.out.println("Please enter the start time : ");
        String startTime = scan.next();
        System.out.println("Please enter the end time : ");
        String endTime = scan.next();

        Session session = timetable.getSession(sessionIdx);
        session.setStartTime(convertStr2Time(startTime));
        session.setEndTime(convertStr2Time(endTime));
    }

    public static void removeSession(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the session index : ");
        String sessionIdx = scan.next();

        timetable.removeSession(sessionIdx);
    }

}
