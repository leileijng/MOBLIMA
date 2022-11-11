package model;
import java.util.*;

/**
 * Cineplex Class
 */
public class Cineplex {
    /**
     * cineplex ID
     */
    private String cineplexID;
    /**
     * cineplex name
     */
    private String cineplexName;
    /**
     * location
     */
    private String location;
    /**
     * List of Cinemas in Cineplex
     */
    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();;

    /**
     *
     * @return cineplex name
     */
    public String getCineplexName() {
        return this.cineplexName;
    }

    /**
     * set cineplex name
     * @param cineplexName
     */
    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    /**
     * set location
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * return cinema by Cinema class
     * @param cinemaCode
     * @return
     */
    public Cinema getCinemas(String cinemaCode) {
        for (int i =0 ; i < cinemas.size(); i ++) {
            if(this.cinemas.get(i).getCinemaCode().equals(cinemaCode)){
                return this.cinemas.get(i);
            }
        }
        return null;
    }

    /**
     * set cineplex id
     * @param cineplexID
     */
    public void setCineplexID(String cineplexID){
        this.cineplexID = cineplexID;
    }
    
    /**
     * @return cineplex id
     */
    public String getCineplexID() {
        return cineplexID;
    }

    public void addCinema(Cinema cinema) {
        this.cinemas.add(cinema);
    }
}