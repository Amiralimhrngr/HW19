package ir.maktabsharif.repository.impl;

import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.BookRepository;
import jakarta.persistence.EntityManagerFactory;

public class BookRepositoryImpl extends GenericRepositoryImpl<Book, Long> implements BookRepository {

    public BookRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Class<Book> getEntityClass() {
        return Book.class;
    }
}
