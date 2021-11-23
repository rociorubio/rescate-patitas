package exceptions;

public class ContraseniaInvalidaException extends RuntimeException {
  public ContraseniaInvalidaException(String causa) { super(causa); }
}
