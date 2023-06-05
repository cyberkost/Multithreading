package homework_05_26_2023;

public class MFU {
    private final Object printLock = new Object();
    private final Object scanLock = new Object();

    public void printDocument(String documentName, int numPages) {
        synchronized (printLock) {
            System.out.println("Печать документа '" + documentName + "' начата.");
            for (int i = 1; i <= numPages; i++) {
                System.out.println("Отпечатана страница " + i + " документа '" + documentName + "'.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Печать документа '" + documentName + "' завершена.");
        }
    }

    public void scanDocument(String documentName, int numPages) {
        synchronized (scanLock) {
            System.out.println("Сканирование документа '" + documentName + "' начато.");
            for (int i = 1; i <= numPages; i++) {
                System.out.println("Отсканирована страница " + i + " документа '" + documentName + "'.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Сканирование документа '" + documentName + "' завершено.");
        }
    }

    public static void main(String[] args) {
        MFU mfu = new MFU();
        new Thread(() -> mfu.printDocument("Документ 1", 5)).start();
        new Thread(() -> mfu.scanDocument("Документ 2", 3)).start();
    }
}
