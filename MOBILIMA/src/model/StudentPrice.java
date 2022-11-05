package model;

public class StudentPrice extends Price{

    public StudentPrice(String movieType, String cinemaClass, int seatType) {
        super(movieType, cinemaClass, seatType);
    }

    @Override
    public double calculatePrice() {
        double price = 0;
        switch (priceTable.getSpecialRules("Student")){
            case OVERRIDE:
                price = priceTable.getPriceByType("Student");
                break;
            case DISCOUNT:
                 price = priceTable.getPriceByType("basic")
                         * priceTable.getDayRate(this.getWeekDay())
                         * priceTable.getCinemaClass(this.getCinemaClass())
                         * priceTable.getMovieTypeRate(this.getMovieType())
                         * 0.8;
                break;
            case NORMAL:
                price = priceTable.getPriceByType("basic")
                        * priceTable.getDayRate(this.getWeekDay())
                        * priceTable.getCinemaClass(this.getCinemaClass())
                        * priceTable.getMovieTypeRate(this.getMovieType());
                break;
        }
        return (double) Math.round(price * 100) / 100;
    }
}
