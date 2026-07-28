package ir.maktabsharif.thread.executor;

public class LibraryTask implements Runnable {

    private final int taskNumber;

    public LibraryTask(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();

        System.out.println("--------------------------------");
        System.out.println("Task " + taskNumber + " started");
        System.out.println("Thread : " + threadName);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("Task " + taskNumber + " finished");
    }
}
