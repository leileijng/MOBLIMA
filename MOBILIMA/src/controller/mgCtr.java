package controller;

import Services.MoviegoerService;
import model.MovieGoer;

public class mgCtr {
    private static MoviegoerService service;
    public static void mainPanel(MovieGoer movieGoer){
        service = new MoviegoerService(movieGoer);
        /// loop until logout
        /// select view all movies
        service.viewAllMovies();
        /// select view movie list by filter
        //service.viewAllSessionsByFilter();
    }
}
