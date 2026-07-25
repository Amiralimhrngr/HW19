package ir.maktabsharif.exception;

public class BookNotFoundException extends BusinessException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
