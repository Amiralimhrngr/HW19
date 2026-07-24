package ir.maktabsharif.model;

import ir.maktabsharif.exception.InvalidDataException;

public class Book extends BaseModel<Long> {

    private String title;
    private String author;
    private Double price;
    private Integer availableCopies;
    private String isbn;

    public Book() {
    }

    public Book(String title, String author, Double price, Integer availableCopies, String isbn) {
        setTitle(title);
        setAuthor(author);
        setPrice(price);
        setAvailableCopies(availableCopies);
        setIsbn(isbn);
    }

    public Book(Long id, String title, String author, Double price, Integer availableCopies, String isbn) {
        super(id);
        setTitle(title);
        setAuthor(author);
        setPrice(price);
        setAvailableCopies(availableCopies);
        setIsbn(isbn);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price == null || price < 0) {
            throw new InvalidDataException("Price can not be null or negative!");
        }
        this.price = price;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        if (availableCopies == null || availableCopies < 0) {
            throw new InvalidDataException("Available copies can not be null or negative!");
        }
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidDataException("Title can not be null or empty!");
        }
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new InvalidDataException("Author can not be null or empty!");
        }
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new InvalidDataException("ISBN can not be null or empty!");
        }
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return String.format("""
                Book
                ID: %d
                Title: %s
                Author: %s
                Price: %.2f
                Available Copies: %d
                ISBN: %s
                """, getId(), getTitle(), getAuthor(), getPrice(), getAvailableCopies(), getIsbn());
    }
}
