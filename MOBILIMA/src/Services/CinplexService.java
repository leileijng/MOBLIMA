package Services;

import model.Cineplex;

public class CinplexService {
    public static Cineplex getCinplexByName(String cinplexName) {
        for(Cineplex c : Service.cineplexes){
            if(c.getCinplexName().equals(cinplexName))
                return c;
        }
        return null;
    }

    public static Cineplex getCinplexById(String cinplexId) {
        for(Cineplex c : Service.cineplexes){
            if(c.getCinplexID().equals(cinplexId))
                return c;
        }
        return null;
    }
}
