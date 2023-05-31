package homework_05_26_2023;

public class PrintLetters implements Runnable {
    private static final Object lock = new Object();
    private static volatile char currentLetter = 'A';
    private final char letter;

    public PrintLetters(char letter) {
        this.letter = letter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (lock) {
                while (currentLetter != letter) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(letter);
                if (currentLetter == 'A') {
                    currentLetter = 'B';
                } else if (currentLetter == 'B') {
                    currentLetter = 'C';
                } else {
                    currentLetter = 'A';
                }
                lock.notifyAll();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void letterThreads() {
        Thread threadA = new Thread(new PrintLetters('A'));
        Thread threadB = new Thread(new PrintLetters('B'));
        Thread threadC = new Thread(new PrintLetters('C'));
        threadA.start();
        threadB.start();
        threadC.start();
    }

    public static void main(String[] args) {
        letterThreads();
    }
}