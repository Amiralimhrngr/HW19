package ir;

import ir.maktabsharif.exception.BookNotFoundException;
import ir.maktabsharif.exception.InvalidDataException;
import ir.maktabsharif.exception.MemberNotFoundException;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.model.Member;
import ir.maktabsharif.service.BookReportService;
import ir.maktabsharif.thread.executor.LibraryTask;
import ir.maktabsharif.thread.producerconsumer.Consumer;
import ir.maktabsharif.thread.producerconsumer.Producer;
import ir.maktabsharif.thread.producerconsumer.Warehouse;
import ir.maktabsharif.thread.race.BorrowTask;
import ir.maktabsharif.thread.race.SharedBook;
import ir.maktabsharif.util.ApplicationContext;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    ========== Library Book Management System ==========
                    1. Add Book
                    2. Update Book
                    3. Delete Book
                    4. Add Member
                    5. Update Member
                    6. Delete Member
                    7. Reports
                    8. Thread Exercises
                        a. Race Condition
                        b. Producer–Consumer
                        c. ExecutorService
                    9. Exit
                    """);
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        Book bookToAdd = bookMenu(scanner);
                        ApplicationContext.getBookService().save(bookToAdd);
                        System.out.println("Book has been saved successfully!");
                    } catch (InputMismatchException | InvalidDataException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Please enter book's ID: ");
                        Long bookToUpdateId = scanner.nextLong();
                        scanner.nextLine();
                        Book bookToUpdate = bookMenu(scanner);
                        bookToUpdate.setId(bookToUpdateId);
                        ApplicationContext.getBookService().update(bookToUpdate);
                        System.out.println("Book has been updated successfully!");
                    } catch (InputMismatchException | InvalidDataException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Please enter book's ID: ");
                        Long bookToDeleteId = scanner.nextLong();
                        scanner.nextLine();
                        Book bookToDelete = ApplicationContext.getBookService().findById(bookToDeleteId);
                        ApplicationContext.getBookService().delete(bookToDelete);
                        System.out.println("Book has been deleted successfully!");
                    } catch (InputMismatchException | BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        Member memberToAdd = memberMenu(scanner);
                        ApplicationContext.getMemberService().save(memberToAdd);
                        System.out.println("Member has been saved successfully!");
                    } catch (InputMismatchException | InvalidDataException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Please enter member's Id: ");
                        Long memberToUpdateId = scanner.nextLong();
                        scanner.nextLine();
                        Member memberToUpdate = memberMenu(scanner);
                        memberToUpdate.setId(memberToUpdateId);
                        ApplicationContext.getMemberService().update(memberToUpdate);
                        System.out.println("Member has been updated successfully!");
                    } catch (InputMismatchException | InvalidDataException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        System.out.println("Please enter member's Id: ");
                        Long memberToDeleteId = scanner.nextLong();
                        scanner.nextLine();
                        Member memberToDelete = ApplicationContext.getMemberService().findById(memberToDeleteId);
                        ApplicationContext.getMemberService().delete(memberToDelete);
                        System.out.println("Member has been deleted successfully!");
                    } catch (InputMismatchException | MemberNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Total books count: " + BookReportService.getTotalBooksCount());
                    System.out.println("Average books price: " + BookReportService.getAverageBookPrice());
                    try {
                        System.out.println("Most expensive book:\n " + BookReportService.getMostExpensiveBook());
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    System.out.println("Please enter an option: ");
                    System.out.println("""
                            a. Race Condition
                            b. Producer–Consumer
                            c. ExecutorService
                            """);
                    String choice1 = scanner.nextLine();
                    switch (choice1) {
                        case "a":
                            try {
                                runUnsafe();
                                runSafe();
                            } catch (InterruptedException e) {
                                System.out.println("Interrupted!");
                            }
                            break;
                        case "b":
                            try {
                                runProducerConsumer();
                            } catch (InterruptedException e) {
                                System.out.println("Interrupted!");
                            }
                            break;
                        case "c":
                            runExecutor();
                            break;
                        default:
                            System.out.println("Please enter a valid option!");
                    }
                    break;

                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Please enter valid number!");
            }
        }
    }

    private static Book bookMenu(Scanner scanner) {
        System.out.println("Please enter book's title: ");
        String bookTitle = scanner.nextLine();
        System.out.println("Please enter book's author: ");
        String bookAuthor = scanner.nextLine();
        System.out.println("Please enter book's price: ");
        double bookPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Please enter book's available copies: ");
        int bookAvailableCopies = scanner.nextInt();
        scanner.nextLine();
        return new Book(bookTitle, bookAuthor, bookPrice, bookAvailableCopies);
    }

    private static Member memberMenu(Scanner scanner) {
        System.out.println("Please enter member's full name: ");
        String memberFullName = scanner.nextLine();
        System.out.println("Please enter member's phone: ");
        String memberPhone = scanner.nextLine();
        return new Member(memberFullName, memberPhone);
    }

    private static void runUnsafe() throws InterruptedException {

        System.out.println("===== Race Condition =====");

        SharedBook book = new SharedBook("Java", 1);

        Thread t1 = new Thread(new BorrowTask(book, false), "Member-1");
        Thread t2 = new Thread(new BorrowTask(book, false), "Member-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Remaining copies : "
                + book.getAvailableCopies());
    }

    public static void runSafe() throws InterruptedException {

        System.out.println("\n===== Using synchronized =====");

        SharedBook book = new SharedBook("Java", 1);

        Thread t1 = new Thread(new BorrowTask(book, true), "Member-1");
        Thread t2 = new Thread(new BorrowTask(book, true), "Member-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Remaining copies : "
                + book.getAvailableCopies());
    }

    public static void runProducerConsumer() throws InterruptedException {

        Warehouse warehouse = new Warehouse();

        Thread producer =
                new Thread(new Producer(warehouse), "Producer");

        Thread consumer =
                new Thread(new Consumer(warehouse), "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Producer-Consumer finished.");
    }

    public static void runExecutor() {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try {

            for (int i = 1; i <= 10; i++) {
                executorService.execute(new LibraryTask(i));
            }

        } finally {

            executorService.shutdown();

            try {

                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {

                    System.out.println("Force shutdown...");

                    executorService.shutdownNow();
                }

            } catch (InterruptedException e) {

                executorService.shutdownNow();
                Thread.currentThread().interrupt();

            }
        }

        System.out.println("All tasks completed.");
    }
}

