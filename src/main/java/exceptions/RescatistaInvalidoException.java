package exceptions;

public class RescatistaInvalidoException extends RuntimeException{
  public RescatistaInvalidoException(String causa) {
    super(causa);
  }
}
