package Services;

import model.Cineplex;

/**
 * Cineplex Service
 */
public class CineplexService {

    /**
     * get the cineplex by name
     * @param cineplexName
     * @return the cineplex in required name
     */
    public static Cineplex getCineplexByName(String cineplexName) {
        for(Cineplex c : Service.cineplexes){
            if(c.getCineplexName().equals(cineplexName))
                return c;
        }
        return null;
    }

    /**
     * get the cineplex by ID
     * @param cineplexId
     * @return the required cineplex
     */
    public static Cineplex getCineplexById(String cineplexId) {
        for(Cineplex c : Service.cineplexes){
            if(c.getCineplexID().equals(cineplexId))
                return c;
        }
        return null;
    }
}
