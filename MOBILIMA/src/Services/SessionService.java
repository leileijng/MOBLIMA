package Services;

import model.*;


import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;

public class SessionService {
    private static int countSession = 0;

    public static Scanner scan = new Scanner(System.in);

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

    public static void addSessionToDB(Session session){
        Service.sessions.add(session);
    }

    public static void addSession(){
        System.out.println("Please enter the movie title : ");
        String movieTitle = scan.nextLine();
        System.out.println("Please enter the cinplex name : ");
        String cinplexName = scan.nextLine();
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
                CinplexService.getCinplexById("ciineA").getCinemas(cinemaCode),
                convertStr2Time(startTime),
                convertStr2Time(endTime)
        );
        Service.sessions.add(session);
    }

    public static Session getSessionByIndex(String msg){
        // showSessions();
        Session session = null;
        do{
            System.out.println(msg);
            String sessionIdx = scan.nextLine();
            session = getSessionBySessionIndex(sessionIdx);
            if(session == null) System.err.println("Please provide valid session ID!");
        }while(session == null);
        return session;
    }

    public static void editSession(){
        Session session = getSessionByIndex("Please enter the session index to edit:");
        System.out.println("Please enter the start time : ");
        String startTime = scan.nextLine();
        System.out.println("Please enter the end time : ");
        String endTime = scan.nextLine();

        session.setStartTime(convertStr2Time(startTime));
        session.setEndTime(convertStr2Time(endTime));
    }

    public static void removeSession(){
        System.out.println("Please enter the session index : ");
        String sessionIdx = scan.nextLine();
        removeSession(sessionIdx);
    }

    public static void showSessions() {
        int num = Service.sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            Service.sessions.get(i).viewDetails();
        }
    }

    public static void showSessionsByMovie(Movie movie) {
        int i = 0;
        for (Session session : Service.sessions) {
            if (session.getMovie().getMovieTitle().equals(movie.getMovieTitle())) {
                System.out.printf("=== Session %d ===\n", ++i);
                session.viewDetails();
            }
        }
    }

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

    public static Session getSessionBySessionIndex(String sessionIndex) {
        int num = Service.sessions.size();
        for (int i = 0; i < num; i ++) {
            if (Objects.equals(Service.sessions.get(i).getSessionIndex(), sessionIndex)) {
                return Service.sessions.get(i);
            }
        }
        return null;
    }

    public String addSession(Session session) {
        Service.sessions.add(session);
        return "Successfully Adding the Session.";
    }
}
