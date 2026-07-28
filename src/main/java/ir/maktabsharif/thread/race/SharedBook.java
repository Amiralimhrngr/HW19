package ir.maktabsharif.thread.race;

public class SharedBook {

    private final String title;
    private int availableCopies;

    public SharedBook(String title, int availableCopies) {
        this.title = title;
        this.availableCopies = availableCopies;
    }

    public boolean borrowUnsafe() {

        return borrowing();
    }

    public synchronized boolean borrow() {

        return borrowing();
    }

    private boolean borrowing() {
        if (availableCopies > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            availableCopies--;

            System.out.println(Thread.currentThread().getName()
                    + " borrowed " + title);

            return true;
        }

        System.out.println(Thread.currentThread().getName()
                + " failed.");

        return false;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
}
