package ir.maktabsharif.service;

import ir.maktabsharif.exception.BookNotFoundException;
import ir.maktabsharif.model.Book;

public interface BookService extends BaseService<Book, Long, BookNotFoundException> {
}
