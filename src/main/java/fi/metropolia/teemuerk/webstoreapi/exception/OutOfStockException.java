package fi.metropolia.teemuerk.webstoreapi.exception;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }
}
