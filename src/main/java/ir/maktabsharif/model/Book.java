package ir.maktabsharif.model;

import ir.maktabsharif.exception.InvalidDataException;

import java.util.Objects;

public class Book extends BaseModel<Long> {

    private String title;
    private String author;
    private Double price;
    private Integer availableCopies;

    public Book() {
    }

    public Book(String title, String author, Double price, Integer availableCopies) {
        setTitle(title);
        setAuthor(author);
        setPrice(price);
        setAvailableCopies(availableCopies);

    }

    public Book(Long id, String title, String author, Double price, Integer availableCopies) {
        super(id);
        setTitle(title);
        setAuthor(author);
        setPrice(price);
        setAvailableCopies(availableCopies);

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
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
                """, getId(), getTitle(), getAuthor(), getPrice(), getAvailableCopies());
    }
}
