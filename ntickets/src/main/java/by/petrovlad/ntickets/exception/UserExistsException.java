package by.petrovlad.ntickets.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException() {
        super();
    }

    public UserExistsException(String message) {
        super(message);
    }
}
