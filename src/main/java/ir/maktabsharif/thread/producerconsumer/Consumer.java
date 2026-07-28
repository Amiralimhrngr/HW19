package ir.maktabsharif.thread.producerconsumer;

public class Consumer implements Runnable {

    private final Warehouse warehouse;

    public Consumer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {

            warehouse.consume();

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}