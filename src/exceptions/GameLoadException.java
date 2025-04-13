package exceptions;

public class GameLoadException extends Exception {
    public GameLoadException(String message) {
        super(message);
    }

    public GameLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
