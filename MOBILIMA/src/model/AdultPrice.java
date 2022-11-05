package model;

public class AdultPrice extends Price{
    public AdultPrice(String movieType, String cinemaClass, int seatType) {
        super(movieType, cinemaClass, seatType);
    }

    @Override
    public double calculatePrice() {
        double price = priceTable.getPriceByType("Basic")
                * priceTable.getDayRate(this.getWeekDay())
                * priceTable.getCinemaClass(this.getCinemaClass())
                * priceTable.getMovieTypeRate(this.getMovieType());
        return (double) Math.round(price * 100) / 100;
    }
}
