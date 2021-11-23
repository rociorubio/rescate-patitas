package exceptions;

public class MascotaInvalidaException extends RuntimeException {
    public MascotaInvalidaException(String causa) {
        super(causa);
    }
}
