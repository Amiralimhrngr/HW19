package ir.maktabsharif.service;


import ir.maktabsharif.exception.BookNotFoundException;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.util.ApplicationContext;

import java.util.Comparator;


public class BookReportService {
    private static final BookService bookService = ApplicationContext.getBookService();

    public static int getTotalBooksCount() {
        return bookService.findAll().size();
    }

    public static double getAverageBookPrice() {
        return bookService.findAll()
                .stream()
                .mapToDouble(Book::getPrice)
                .average()
                .orElse(0);
    }

    public static Book getMostExpensiveBook() throws BookNotFoundException {
        return bookService.findAll()
                .stream()
                .max(Comparator.comparingDouble(Book::getPrice))
                .orElseThrow(() -> new BookNotFoundException("Couldn't find most expensive book!"));
    }
}
