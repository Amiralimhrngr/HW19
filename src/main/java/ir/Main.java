package ir;

import ir.maktabsharif.exception.BookNotFoundException;
import ir.maktabsharif.exception.InvalidDataException;
import ir.maktabsharif.exception.MemberNotFoundException;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.model.Member;
import ir.maktabsharif.service.BookReportService;
import ir.maktabsharif.util.ApplicationContext;

import java.util.InputMismatchException;
import java.util.Scanner;

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
                    //TODO
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
}
