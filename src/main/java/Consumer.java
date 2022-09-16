public class Consumer extends Thread {

    private final Dealer dealer;
    static int nameCounter = 0;

    public Consumer(Dealer dealer) {
        this.dealer = dealer;
        super.setName("Покупатель " + nameCounter);
        nameCounter++;
    }

    @Override
    public void run() {
        System.out.printf("%s зашел в автосалон\n", getName());
        dealer.buyCar();
    }
}
