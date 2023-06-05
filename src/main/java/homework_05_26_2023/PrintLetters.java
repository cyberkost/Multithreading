package homework_05_26_2023;

public class PrintLetters implements Runnable {
    private static final Object LOCK = new Object();
    private static volatile char currentLetter = 'A';
    private final char letter;

    public PrintLetters(char letter) {
        this.letter = letter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (LOCK) {
                while (currentLetter != letter) {
                    try {
                        LOCK.wait();
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
                LOCK.notifyAll();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void letterThreads() {
        new Thread(new PrintLetters('A')).start();
        new Thread(new PrintLetters('B')).start();
        new Thread(new PrintLetters('C')).start();
    }

    public static void main(String[] args) {
        letterThreads();
    }
}