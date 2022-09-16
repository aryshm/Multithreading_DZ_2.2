import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Dealer {

    List<Car> list = new LinkedList<>();
    private static final int PRODUCTION_TIME = 3000;
    private static final int CARS_TO_PRODUCE = 10;
    private static final int FIRST_CAR_TO_TAKE = 0;
    private static boolean flag = true;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public void buyCar() {
        lock.lock();
        try {
            while (flag) {
                if (list.isEmpty()) {
                    System.out.println("Машин нет");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    list.remove(FIRST_CAR_TO_TAKE);
                    System.out.printf("%s уехал на новеньком авто\n", Thread.currentThread().getName());
                    flag = false;
                }
            }
            flag = true;
        } finally {
            lock.unlock();
        }
    }

    public void produceCar() {
        for (int i = CARS_TO_PRODUCE; i > 0; i--) {
            try {
                Thread.sleep(PRODUCTION_TIME);
            } catch (InterruptedException e) {
                return;
            }
            lock.lock();
            try {
                System.out.println("Производитель Toyota выпустил 1 авто");
                list.add(new Car());
            } finally {
                condition.signal();
                lock.unlock();
            }
        }
    }

}
