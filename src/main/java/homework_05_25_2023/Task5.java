package homework_05_25_2023;

public class Task5 implements Runnable {
    private final String name;

    public Task5(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name);
        }
    }

    private static void nameOfThread() {
        Thread thread1 = new Thread(new Task5("Thread 1"));
        Thread thread2 = new Thread(new Task5("Thread 2"));
        Thread thread3 = new Thread(new Task5("Thread 3"));
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void main(String[] args) {
        nameOfThread();
    }
}
