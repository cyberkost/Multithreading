package store;

public class Seller implements Runnable {
    private final Store store;

    Seller(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            store.get();
        }
    }
}
