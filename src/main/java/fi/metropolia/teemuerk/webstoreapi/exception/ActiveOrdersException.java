package fi.metropolia.teemuerk.webstoreapi.exception;

public class ActiveOrdersException extends RuntimeException {
    public ActiveOrdersException(String message) {
        super(message);
    }
}
