package homework_05_25_2023;

public class Task2 implements Runnable {
    private static final Object LOCK = new Object();
    private static int current = 1;
    private final int start;
    private final int end;

    public Task2(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            synchronized (LOCK) {
                while (i != current) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int square = i * i;
                System.out.println(square);
                current++;
                LOCK.notifyAll();
            }
        }
    }

    public static void threads() {
        Thread thread1 = new Thread(new Task2(1, 3));
        Thread thread2 = new Thread(new Task2(4, 7));
        Thread thread3 = new Thread(new Task2(8, 10));
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void main(String[] args) {
        threads();
    }
}