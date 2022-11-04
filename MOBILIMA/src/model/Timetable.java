package model;
import java.io.*;
import java.util.*;

public class Timetable {
    private Vector<Session> sessions = new Vector<>();

    public Timetable() {

    }
    public Timetable(int numOfSession, Session[] sessions) {
        for (int i = 0; i < numOfSession; i ++) {
            this.sessions.add(sessions[i]);
        }
    }

    public void showSessions() {
        int num = this.sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            this.sessions.get(i).viewDetails();
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

    public String removeSession(String sessionIndex) {
        int num = this.sessions.size();
        for (int i = 0; i < num; i ++) {
            if (this.sessions.get(i).getSessionIndex() == sessionIndex) {//incompatable type, string and int
                this.sessions.remove(i);
                return "Successfully Removing the Session.";
            }
        }
        return "Can't Find this Session Index.";
    }

    public Session getSession(String sessionIndex) {
        int num = this.sessions.size();
        for (int i = 0; i < num; i ++) {
            if (this.sessions.get(i).getSessionIndex() == sessionIndex) {
                return this.sessions.get(i);
            }
        }
        return null;
    }

    public String addSession(Session session) {
        this.sessions.add(session);
        return "Successfully Adding the Session.";
    }
}