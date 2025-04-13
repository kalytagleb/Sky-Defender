package exceptions;

public class GameSaveException extends Exception {
    public GameSaveException(String message) {
        super(message);
    }

    public GameSaveException(String message, Throwable cause) {
      super(message, cause);
    }
}