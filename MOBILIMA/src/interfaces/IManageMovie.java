package interfaces;
/**
 * interface movie management
 */
public interface IManageMovie {
    /**
     * add new movie
     */
    public abstract void addNewMovie();

    /**
     * update movie details
     */
    public abstract void updateMovieDetails();

    /**
     * unlist movie
     */
    public abstract void unlistMovie();
}
