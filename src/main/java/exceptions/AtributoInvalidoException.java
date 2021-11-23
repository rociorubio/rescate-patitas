package exceptions;

public class AtributoInvalidoException extends RuntimeException{
    public AtributoInvalidoException(String causa) {
        super(causa);
    }
}
