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
     * @param cineplexName the name of the cineplex
     */
    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    /**
     * set location
     * @param location the location of the cineplex
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * return cinema by Cinema class
     * @param cinemaCode code of the cinema
     * @return a cinema object
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
     * @param cineplexID the id of the cineplex
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

    /**
     * Add a cinema to the list
     * @param cinema the cinema to be added
     */
    public void addCinema(Cinema cinema) {
        this.cinemas.add(cinema);
    }
}