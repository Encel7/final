package by.epam.shop.exceptions;

public class ConstantException extends Exception {
    public ConstantException() {}

    public ConstantException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstantException(String message) {
        super(message);
    }

    public ConstantException(Throwable cause) {
        super(cause);
    }
}
