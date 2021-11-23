package exceptions;

public class MascotaPerdidaInvalidaException extends RuntimeException {
    public MascotaPerdidaInvalidaException(String causa) {
        super(causa);
    }
}
