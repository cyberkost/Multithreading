package store;

public class Producer implements Runnable{
    private final Store store;

    Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            store.put();
        }
    }

}
