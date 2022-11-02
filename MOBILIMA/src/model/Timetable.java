package model;
import java.io.*;
import java.util.*;

class Timetable {
    private Vector<Session> sessions = new Vector<>();

    public Timetable(int numOfSession, Session[] sessions) {
        for (int i = 0; i < numOfSession; i ++) {
            this.sessions.add(sessions[i]);
        }
    }

    public void showSessions() {
        int num = this.sessions.size();
        System.out.println("total number of sessions : " + num);
        for (int i = 0; i < num; i ++) {
            this.sessions[i].viewDetails();
        }
    }

    public void showSessionsAtCinema(String cinemaCode) {
        int num = this.sessions.size();
        for (int i = 0; i < num; i ++) {
            if (this.sessions[i].cinemaCode == cinemaCode) {
                this.sessions[i].viewDetails();
            }
        }
    }

    public String removeSession(int sessionIndex) {
        int num = this.sessions.size();
        for (int i = 0; i < num; i ++) {
            if (this.sessions[i].sessionIndex == sessionIndex) {
                this.sessions.remove(i);
                return "Successfully Removing the Session.";
            }
        }
        return "Can't Find this Session Index.";
    }

    public String addSession(Session session) {
        this.sessions.add(session);
        return "Successfully Adding the Session.";
    }
}