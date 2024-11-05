package ajfr.diamond.kata.exception;

public class ValidationException extends Exception {

    private final String rawInput;

    public ValidationException(String message) {
        super(message);
        this.rawInput = "";
    }

    public ValidationException(String message, String rawInput) {
        super(message);
        this.rawInput = rawInput;
    }

    public String getRawInput() {
        return rawInput;
    }
}
