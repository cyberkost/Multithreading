package homework_05_25_2023;

public class Task1 implements Runnable {
    @Override
    public void run() {
        synchronized (System.out) {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
            }
        }
    }

    public static void go() {
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new Task1());
            thread.start();
        }
    }

    public static void main(String[] args) {
        go();
    }
}

