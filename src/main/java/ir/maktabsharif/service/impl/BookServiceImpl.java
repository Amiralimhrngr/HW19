package ir.maktabsharif.service.impl;

import ir.maktabsharif.exception.BookNotFoundException;
import ir.maktabsharif.exception.InvalidDataException;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.BookRepository;
import ir.maktabsharif.service.BookService;

public class BookServiceImpl extends BaseServiceImpl<Book,
        Long,
        BookRepository,
        BookNotFoundException>
        implements BookService {
    public BookServiceImpl(BookRepository repository) {
        super(repository, () -> new BookNotFoundException("Book not found!"));
    }

    @Override
    public void validation(Book book) {
        if (book.getTitle() == null || book.getTitle().isBlank()) {
            throw new InvalidDataException("Title can not be null or empty!");
        }
        if (book.getAuthor() == null || book.getAuthor().isBlank()) {
            throw new InvalidDataException("Author can not be null or empty!");
        }
        if (book.getPrice() == null || book.getPrice() < 0) {
            throw new InvalidDataException("Price can not be null or negative!");
        }
        if (book.getAvailableCopies() == null || book.getAvailableCopies() < 0) {
            throw new InvalidDataException("Available copies can not be null or negative!");
        }
    }
}
