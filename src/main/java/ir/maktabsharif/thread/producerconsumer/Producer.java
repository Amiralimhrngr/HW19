package ir.maktabsharif.thread.producerconsumer;

import ir.maktabsharif.model.Book;

public class Producer implements Runnable {

    private final Warehouse warehouse;

    public Producer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {

            Book book = new Book();

            book.setTitle("Book-" + i);
            book.setAuthor("Author-" + i);
            book.setPrice(100.0 + i);
            book.setAvailableCopies(1);

            warehouse.produce(book);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
