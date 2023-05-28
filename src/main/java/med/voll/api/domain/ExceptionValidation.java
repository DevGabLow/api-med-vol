package med.voll.api.domain;

public class ExceptionValidation extends RuntimeException {
    public ExceptionValidation(String message) {
        super(message);
    }
}
