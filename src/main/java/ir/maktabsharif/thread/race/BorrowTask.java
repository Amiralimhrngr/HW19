package ir.maktabsharif.thread.race;

public class BorrowTask implements Runnable {

    private final SharedBook book;
    private final boolean safe;

    public BorrowTask(SharedBook book, boolean safe) {
        this.book = book;
        this.safe = safe;
    }

    @Override
    public void run() {

        if (safe) {
            book.borrow();
        } else {
            book.borrowUnsafe();
        }
    }
}
