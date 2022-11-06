package Services;

import model.MovieGoer;
import model.Payment;
import model.Review;

import java.util.List;

public class MoviegoerService extends Service{
    private MovieGoer movieGoer;
    private List<Review> reviews;
    private List<Payment> payments;

    public MoviegoerService(MovieGoer mg){
        this.movieGoer = mg;
    }


}
