package store;

public class Store {
    private int counter = 0;
    private int saleCounter = 0;

    public synchronized void put() {
        while (counter >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        counter++;
        System.out.println("+1 : Хлеб заказали");
        System.out.println("\tколичество хлеба на складе : " + counter);
        notify();
    }

    public synchronized void get() {
        while (counter < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        counter--;
        saleCounter++;
        System.out.println("-1 : Хлеб забрали");
        System.out.println("\tколичество хлеба у продавца : " + saleCounter);
        System.out.println("\tколичество хлеба на складе : " + counter);
        if (counter == 0 && saleCounter == 5) notify();
    }

    public synchronized void buy() {
        while (saleCounter < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(500);
            saleCounter--;
            counter++;
            System.out.println("+1 : Хлеб купили");
            System.out.println("\tколичество хлеба у потребителя : " + counter);
            System.out.println("\tколичество хлеба у продавца : " + saleCounter);
            if (counter == 0 && saleCounter == 5) notify();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
