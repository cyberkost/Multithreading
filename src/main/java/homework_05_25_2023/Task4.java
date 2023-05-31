package homework_05_25_2023;

public class Task4 implements Runnable {
    private static final Object LOCK = new Object();

    @Override
    public void run() {
        synchronized (LOCK) {
            try {
                LOCK.wait();
                System.out.println("Hello, World!");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOCK.notify();
        }
    }

    public static void serialOutput() {
        Thread thread1 = new Thread(new Task4());
        Thread thread2 = new Thread(new Task4());
        Thread thread3 = new Thread(new Task4());
        Thread thread4 = new Thread(new Task4());
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            Thread.sleep(1000);
            synchronized (Task4.LOCK) {
                Task4.LOCK.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        serialOutput();
    }
}
