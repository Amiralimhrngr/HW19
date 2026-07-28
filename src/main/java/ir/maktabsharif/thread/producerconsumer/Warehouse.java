package ir.maktabsharif.thread.producerconsumer;

import ir.maktabsharif.model.Book;

public class Warehouse {

    private Book book;

    public synchronized void produce(Book newBook) {

        while (book != null) {

            try {
                System.out.println("Warehouse is full. Producer waits...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        book = newBook;

        System.out.println(Thread.currentThread().getName()
                + " stored : "
                + newBook.getTitle());

        notifyAll();
    }

    public synchronized Book consume() {

        while (book == null) {

            try {
                System.out.println("Warehouse is empty. Consumer waits...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        Book shippedBook = book;

        book = null;

        System.out.println(Thread.currentThread().getName()
                + " shipped : "
                + shippedBook.getTitle());

        notifyAll();

        return shippedBook;
    }
}
