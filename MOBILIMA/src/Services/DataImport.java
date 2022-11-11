package Services;

import model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static Services.MovieService.addMovieToDB;

/**
 * Import the Data
 */
public class DataImport {

    /**
     * load the cineplex
     * @return the list of cineplex
     */
    public static List<Cineplex> loadCineplexFromFile(){
        List<Cineplex> cineplexes = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("data/Cineplex.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Cineplex cineplex = new Cineplex();
                List<String> row_str = Arrays.asList(line.split(","));
                cineplex.setCineplexID(row_str.get(0).replaceAll("\\p{C}", ""));
                cineplex.setCineplexName(row_str.get(1).replaceAll("\\p{C}", ""));
                cineplex.setLocation(row_str.get(2).replaceAll("\\p{C}", ""));
                cineplexes.add(cineplex);
            }
        } catch (IOException e) {
            System.err.println("Cannot get the cineplex file, please check again!");
        }
        return cineplexes;
    }

    /**
     * load the cinema
     * @return the list of cinemas
     */
    public static List<Cinema> loadCinemaFromFile(){
        List<Cinema> cinemas = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("data/Cinema.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Cinema cinema = new Cinema();
                List<String> row_str = Arrays.asList(line.split(","));
                cinema.setCinemaCode(row_str.get(1).replaceAll("\\p{C}", ""));

                cinema.setClassOfCinema(ClassOfCinema.valueOf(row_str.get(2).replaceAll("\\p{C}", "").toUpperCase()));
                cinema.setLayout(getLayoutById(row_str.get(3).replaceAll("\\p{C}", "")));

                /*
                for(Cineplex c : Service.cineplexes){
                    if(c.getCineplexID().equals(row_str.get(0).replaceAll("\\p{C}", ""))){
                        c.addCinema(cinema);
                        cinema.setCineplex(c);
                    }
                }
                cinemas.add(cinema);*/
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        }
        return cinemas;
    }

    /**
     * import the single layout
     * @param filepath
     * @param index
     * @return the layout
     */
    public static Layout importSingleLayout(String filepath, int index){
        ArrayList<Integer> s = new ArrayList<>();
        int row = 0;
        int col = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                row++;
                List<String> row_str = Arrays.asList(line.split(","));
                col = row_str.size() > col ? row_str.size():col;
                for(String cell : row_str) s.add(Integer.valueOf(cell.replaceAll("\\p{C}", "")));
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        }
        return new Layout(("layout_" + index), row, col, s);
    }

    /**
     * import yhe layouts
     * @return the list of layouts
     */
    public static List<Layout> importLayouts() {
        List<Layout> layouts = new ArrayList<>();
        String[] files = new File("data/layout").list();
        int count = 0;
        for(String f : files){
            Layout l = importSingleLayout("data/layout/" + f, ++count);
            layouts.add(l);
        }
        return layouts;
    }

    /**
     * import yhe movies
     */
    public static void importMovies(){
        try(BufferedReader br = new BufferedReader(new FileReader("data/Movie.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Movie movie = new Movie();
                List<String> row_str = Arrays.asList(line.split(","));
                movie.setMovieTitle(row_str.get(0).replaceAll("\\p{C}", ""));
                movie.setClassification(Classification.valueOf(row_str.get(1).replaceAll("\\p{C}", "")));
                movie.setShowingStatus(ShowingStatus.valueOf(row_str.get(2).replaceAll("\\p{C}", "").trim()));

                movie.setSynopsis(row_str.get(3).replaceAll("\\p{C}", "").trim());
                movie.setDirector(row_str.get(4).replaceAll("\\p{C}", "").trim());

                String rating_s = row_str.get(5).replaceAll("\\p{C}", "").trim();
                if(!rating_s.equals("NA")) movie.setOverallRating(Double.parseDouble(rating_s));
                movie.setDateEndOfShowing(new SimpleDateFormat("dd/MM/yyyy").parse(row_str.get(6).replaceAll("\\p{C}", "")));
                String[] casts= row_str.get(7).replaceAll("\\p{C}", "").split(";");
                movie.setCasts(casts);

                String revenue_s = row_str.get(8).replaceAll("\\p{C}", "").trim();
                if(!revenue_s.equals("NA"))movie.setTotalRevenue(Double.parseDouble(revenue_s));
                movie.setMovieType(row_str.get(9).replaceAll("\\p{C}", "").trim());
                addMovieToDB(movie);
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * import the sessions
     * @return the list of sessions
     */
    public static List<Session> importSessions(){
        List<Session> sessions = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("data/Session.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                List<String> row_str = Arrays.asList(line.split(","));
                String sessionId = row_str.get(0).replaceAll("\\p{C}", "");
                Movie movie = getMovieByTitle(row_str.get(1).replaceAll("\\p{C}", ""));
                Cinema cinema = getCinemaByCode(row_str.get(2).replaceAll("\\p{C}", ""));


                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
                Date parsedDateStart = dateFormat.parse(row_str.get(3).replaceAll("\\p{C}", "").trim());
                Date parsedEndStart = dateFormat.parse(row_str.get(4).replaceAll("\\p{C}", "").trim());
                Timestamp startTime = new java.sql.Timestamp(parsedDateStart.getTime());
                Timestamp endTime = new java.sql.Timestamp(parsedEndStart.getTime());
                Session session = new Session(sessionId, movie, cinema, startTime, endTime);

                sessions.add(session);
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return sessions;
    }

    /**
     * import the reviews
     * @return the list of reviews
     */
    public static List<Review> importReviews(){
        List<Review> reviews = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("data/Review.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                List<String> row_str = Arrays.asList(line.split(","));
                Movie m = getMovieByTitle(row_str.get(0).replaceAll("\\p{C}", "").trim());
                String userId = row_str.get(1).replaceAll("\\p{C}", "").trim();
                double rating = Double.parseDouble(row_str.get(2).replaceAll("\\p{C}", "").trim());
                String comments = row_str.get(3).replaceAll("\\p{C}", "").trim();

                Review review = new Review(userId,m,rating,comments);
                m.addReview(review);
                reviews.add(review);
                MovieGoer movieGoer = getMoviegoerByName(userId);
                movieGoer.addReview(review);

            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        }
        return reviews;
    }

    /**
     * import the moviegoers
     * @return the list of moviegoers
     */
    public static List<MovieGoer> importMoviegoers(){
        List<MovieGoer> movieGoers = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("data/Movie-goer.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                List<String> row_str = Arrays.asList(line.split(","));
                MovieGoer movieGoer = new MovieGoer(
                        row_str.get(0).replaceAll("\\p{C}", "").trim());

                //TODO not sure about phone email

                movieGoers.add(movieGoer);
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        }
        return movieGoers;
    }

    /**
     * import the payment
     * @return the list of payment
     */
    public static List<Payment> importPayment(){
        List<Payment> payments = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("data/Payment.csv"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                List<String> row_str = Arrays.asList(line.split(","));
                MovieGoer movieGoer = getMoviegoerByName(
                        row_str.get(0).replaceAll("\\p{C}", "").trim());
                String TID = row_str.get(1).replaceAll("\\p{C}", "").trim();
                Session session = getSessionById(row_str.get(2).replaceAll("\\p{C}", "").trim());
                String seatIndex = row_str.get(4).replaceAll("\\p{C}", "").trim();
                double price = Double.parseDouble(row_str.get(5).replaceAll("\\p{C}", "").trim());
                session.occupySeat(seatIndex, price);
                Payment payment = new Payment(TID, session, movieGoer, price);
                payments.add(payment);
                movieGoer.addPayments(payment);
            }
        } catch (IOException e) {
            System.err.println("Cannot get the layout file, please check again!");
        }
        return payments;
    }

    /**
     * get the movie by the title
     * @param title
     * @return the movie
     */
    private static Movie getMovieByTitle(String title){
        for(Movie movie : Service.movieList){
            if(movie.getMovieTitle().equals(title))
                return movie;
        }
        return null;
    }

    /**
     * get the cinema by the code
     * @param title
     * @return the cinema
     */
    private static Cinema getCinemaByCode(String title){
        for(Cinema cinema : Service.cinemas){
            if(cinema.getCinemaCode().equals(title))
                return cinema;
        }
        return null;
    }

    /**
     * get the moviegoer by the name
     * @param userName
     * @return the moviegoer
     */
    private static MovieGoer getMoviegoerByName(String userName){
        for(MovieGoer m : Service.movieGoerList){
            if(m.getUsername().equals(userName))
                return m;
        }
        return null;
    }

    /**
     * get the session by the id
     * @param sessionId
     * @return the session
     */
    private static Session getSessionById(String sessionId){
        for(Session s : Service.sessions){
            if(s.getSessionIndex().equals(sessionId))
                return s;
        }
        return null;
    }

    /**
     * get the layout by the id
     * @param id
     * @return the list of cineplex
     */
    private static Layout getLayoutById(String id){
        for(Layout l : Service.layouts){
            if(l.getLayout_id().equals(id)) return l;
        }
        return null;
    }

}
