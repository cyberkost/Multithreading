package store;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Seller seller = new Seller(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(seller).start();
        new Thread(consumer).start();
    }
}