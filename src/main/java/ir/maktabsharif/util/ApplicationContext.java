package ir.maktabsharif.util;

import ir.maktabsharif.repository.*;
import ir.maktabsharif.repository.impl.BookRepositoryImpl;
import ir.maktabsharif.repository.impl.MemberRepositoryImpl;
import ir.maktabsharif.service.BookService;
import ir.maktabsharif.service.MemberService;
import ir.maktabsharif.service.impl.BookServiceImpl;
import ir.maktabsharif.service.impl.MemberServiceImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ApplicationContext {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    private static final BookRepository BOOK_REPOSITORY;
    private static final BookService BOOK_SERVICE;

    private static final MemberRepository MEMBER_REPOSITORY;
    private static final MemberService MEMBER_SERVICE;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("postgres-pu");

        BOOK_REPOSITORY = new BookRepositoryImpl(ENTITY_MANAGER_FACTORY);
        BOOK_SERVICE = new BookServiceImpl(BOOK_REPOSITORY);

        MEMBER_REPOSITORY = new MemberRepositoryImpl(ENTITY_MANAGER_FACTORY);
        MEMBER_SERVICE = new MemberServiceImpl(MEMBER_REPOSITORY);
    }

    public static BookService getBookService() {
        return BOOK_SERVICE;
    }

    public static MemberService getMemberService() {
        return MEMBER_SERVICE;
    }
}
