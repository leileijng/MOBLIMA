package service;

import model.*;


import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;

/**
 * Session Service
 */
public class SessionService {
    /**
     * the count of session
     */
    private static int countSession = 0;

    /**
     * scanner
     */
    public static Scanner scan = new Scanner(System.in);

    /**
     * convert the str to time
     * @param time time to convert in string
     * @return converted time stamp
     */
    public static Timestamp convertStr2Time(String time){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(time);
            return new Timestamp(parsedDate.getTime());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * add the session to data base
     * @param session session to add
     */
    public static void addSessionToDB(Session session){
        Service.sessions.add(session);
    }

    /**
     * add the session
     */
    public static void addSession(){
        System.out.println("Please enter the movie title : ");
        String movieTitle = scan.nextLine();
        System.out.println("Please enter the cineplex name : ");
        String cineplexName = scan.nextLine();
        System.out.println("Please enter the cinema code : ");
        String cinemaCode = scan.nextLine();
        System.out.println("Please enter the start time : ");
        String startTime = scan.nextLine();
        System.out.println("Please enter the end time : ");
        String endTime = scan.nextLine();

        countSession += 1;
        String sessionIndex = "sessionIdx#" + countSession;

        Movie movie = MovieService.getMovieByName(movieTitle);
        if(movie.getShowingStatus() == ShowingStatus.ENDOFSHOWING){
            System.out.println("Failure! The movie is end of showing.");
            return ;
        }

        Session session = new Session(sessionIndex,
                movie,
                CineplexService.getCineplexById("ciineA").getCinemas(cinemaCode),
                convertStr2Time(startTime),
                convertStr2Time(endTime)
        );
        Service.sessions.add(session);
    }

    /**
     * get the session by index
     * @param msg msg to send to the user
     * @return session
     */
    public static Session getSessionByIndex(String msg){
        // showSession();
        Session session = null;
        do{
            System.out.println(msg);
            String sessionIdx = scan.nextLine();
            session = getSessionBySessionIndex(sessionIdx);
            if(session == null) System.err.println("Please provide valid session ID!");
        }while(session == null);
        return session;
    }

    /**
     * edit the session
     */
    public static void editSession(){
        Session session = getSessionByIndex("Please enter the session index to edit:");
        System.out.println("Please enter the start time : ");
        String startTime = scan.nextLine();
        System.out.println("Please enter the end time : ");
        String endTime = scan.nextLine();

        session.setStartTime(convertStr2Time(startTime));
        session.setEndTime(convertStr2Time(endTime));
    }

    /**
     * remove the session
     */
    public static void removeSession(){
        System.out.println("Please enter the session index : ");
        String sessionIdx = scan.nextLine();
        removeSession(sessionIdx);
    }

    /**
     * show the sessions
     */
    public static void showSessions() {
        int num = Service.sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            Service.sessions.get(i).viewDetails();
        }
    }

    /**
     * show the sessions by the movie
     * @param movie movie to find sessions
     */
    public static void showSessionsByMovie(Movie movie) {
        int i = 0;
        for (Session session : Service.sessions) {
            if (session.getMovie().getMovieTitle().equals(movie.getMovieTitle())) {
                Date date = new Date(session.getStartTime().getTime());
                if (date.compareTo(session.getMovie().getDateEndOfShowing()) >= 0)
                    continue;
                System.out.printf("=== Session %d ===\n", ++i);
                session.viewDetails();
            }
        }
    }

    /**
     * remove the session
     * @param sessionIndex index of the session
     * @return status
     */
    public static String removeSession(String sessionIndex) {
        int num = Service.sessions.size();
        for (int i = 0; i < num; i ++) {
            if (Objects.equals(Service.sessions.get(i).getSessionIndex(), sessionIndex)) {//incompatable type, string and int
                Service.sessions.remove(i);
                return "Successfully Removing the Session.";
            }
        }
        return "Can't Find this Session Index.";
    }

    /**
     * get the session by session index
     * @param sessionIndex index of the session
     * @return session
     */
    public static Session getSessionBySessionIndex(String sessionIndex) {
        int num = Service.sessions.size();
        for (int i = 0; i < num; i ++) {
            if (Objects.equals(Service.sessions.get(i).getSessionIndex(), sessionIndex)) {
                return Service.sessions.get(i);
            }
        }
        return null;
    }

    /**
     * add the session
     * @param session session to add
     * @return status
     */
    public String addSession(Session session) {
        Service.sessions.add(session);
        return "Successfully Adding the Session.";
    }
}
