package homework_06_05_2023;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        CountDownLatch startLatch = new CountDownLatch(CARS_COUNT);
        CountDownLatch finishLatch = new CountDownLatch(1);

        Semaphore semaphore = new Semaphore(CARS_COUNT / 2);
        Race race = new Race(new Road(60), new Tunnel(semaphore), new Road(40 ));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10),  startLatch, finishLatch);
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            finishLatch.await();
            System.out.println(Car.WINNER + " WIN");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
