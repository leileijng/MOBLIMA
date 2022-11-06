package model;
import java.io.*;
import java.util.*;

public class Cinema {
    public enum ClassOfCinema { GOLD, MAX, NORMAL }

    public Cineplex cineplex;
	private ClassOfCinema classOfCinema;
    private String cinemaCode;
    private Layout layout;

    public Cinema() {

    }

    public Cineplex getCineplex() {
        return cineplex;
    }

    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    public void setClassOfCinema(ClassOfCinema classOfCinema) {
        this.classOfCinema = classOfCinema;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    public String getCinemaCode() {
        return this.cinemaCode;
    }

    public ClassOfCinema getClassOfCinema() {
        return this.classOfCinema;
    }

    public void viewDetails() {
        System.out.println("=========================================================");
        System.out.println("cinema code  : " + this.cinemaCode);
        System.out.println("cinema class : " + this.classOfCinema);
        //this.layout.viewLayout();
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }

}
