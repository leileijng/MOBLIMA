package model;
import sun.security.x509.CertificateIssuerName;

import java.io.*;
import java.util.*;

public class Cinema {
    public enum ClassOfCinema { GOLD, MAX, NORMAL }
	private ClassOfCinema classOfCinema;
    private String cinemaCode;
    private Layout layout;

    public Cinema(String cinemaCode, ClassOfCinema classOfCinema){

    }
    public Cinema(String cinemaCode, ClassOfCinema classOfCinema, Layout layout) {
        this.cinemaCode = cinemaCode;
        this.classOfCinema = classOfCinema;
        this.layout = layout;
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
        this.layout.viewLayout();
    }

    public Layout getLayout() {
        return layout;
    }

}