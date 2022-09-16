public class Manufacturer extends Thread {

    private final Dealer dealer;

    public Manufacturer(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public void run() {
        dealer.produceCar();
    }
}
