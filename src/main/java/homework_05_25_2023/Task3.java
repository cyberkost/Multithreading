package homework_05_25_2023;

public class Task3 {
    static class EvenNumbersPrint implements Runnable {
        @Override
        public void run() {
            for (int i = 2; i <= 20; i += 2) {
                System.out.println(i);
            }
        }
    }

    static class OddNumbersPrint implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 20; i += 2) {
                System.out.println(i);
            }
        }
    }

    public static void threads() {
        Thread evenThread = new Thread(new EvenNumbersPrint());
        Thread oddThread = new Thread(new OddNumbersPrint());
        evenThread.start();
        oddThread.start();
    }

    public static void main(String[] args) {
        threads();
    }
}

