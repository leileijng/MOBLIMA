package model;

/**
 * Cinema Class
 */
public class Cinema {
    /**
     * The cineplex the cinema belongs to
     */
    public Cineplex cineplex;
    /**
     * The class of Cinema: taking enumeration
     */
	private ClassOfCinema classOfCinema;
    /**
     * The cinemacode of cinema
     */
    private String cinemaCode;
    /**
     * The layout of cinema
     */
    private Layout layout;

    /**
     * constructor of Cinema class
     */
    public Cinema() {

    }

    /**
     *
     * @return corresponding Cineplex
     */
    public Cineplex getCineplex() {
        return cineplex;
    }

    /**
     * set a cineplex for cinema
     * @param cineplex
     */
    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    /**
     * set cinemaClass for cinema
     * @param classOfCinema
     */
    public void setClassOfCinema(ClassOfCinema classOfCinema) {
        this.classOfCinema = classOfCinema;
    }

    /**
     * set cinemaCode for cinema
     * @param cinemaCode
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /**
     *
     * @return cinemaCode
     */
    public String getCinemaCode() {
        return this.cinemaCode;
    }

    /**
     *
     * @return ClassOfCinema
     */
    public ClassOfCinema getClassOfCinema() {
        return this.classOfCinema;
    }

    /**
     * print details of a cinema
     */
    public void viewDetails() {
        System.out.println("=========================================================");
        System.out.println("cinema code  : " + this.cinemaCode);
        System.out.println("cinema class : " + this.classOfCinema);
        //this.layout.viewLayout();
    }

    /**
     * set layout for a cinema
     * @param layout
     */
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * return the layout of cinema
     * @return
     */
    public Layout getLayout() {
        return layout;
    }

}
