package homework_05_26_2023;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile implements Runnable {
    private static final String FILE_NAME = "src/main/java/homework_05_26_2023/output.txt";
    private static final int NUM_LINES = 10;
    private final String threadName;

    public WriteToFile(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {
            for (int i = 1; i <= NUM_LINES; i++) {
                String line = threadName + " - Line " + i + "\n";
                fileWriter.write(line);
                fileWriter.flush();
                Thread.sleep(20);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void threadsFileWriter() {
        Thread thread1 = new Thread(new WriteToFile("Thread 1"));
        Thread thread2 = new Thread(new WriteToFile("Thread 2"));
        Thread thread3 = new Thread(new WriteToFile("Thread 3"));
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void main(String[] args) {
        threadsFileWriter();
    }
}
