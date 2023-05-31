package homework_05_25_2023;

public class Counter {
    private int count;

    public synchronized void increment() {
        for (int i = 0; i < 1_000_000; i++) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }

    public static void increaseTwoThreads() throws InterruptedException {
        Counter counter = new Counter();
        Thread thread1 = new Thread(() -> {
            synchronized (counter) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (counter) {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(counter.getCount());
    }

    public static void main(String[] args) {
        try {
            increaseTwoThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}