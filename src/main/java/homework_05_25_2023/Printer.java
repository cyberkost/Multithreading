package homework_05_25_2023;

public class Printer {
    public synchronized void print(String message) {
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void startThreads() {
        Printer printer = new Printer();
        Thread thread1 = new Thread(() -> {
            synchronized (printer) {
                printer.print("Thread 1");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (printer) {
                printer.print("Thread 2");
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        startThreads();
    }
}
