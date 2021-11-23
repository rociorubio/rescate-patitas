package exceptions;

public class RespuestaInvalidaException extends RuntimeException{
  public RespuestaInvalidaException(String causa) { super(causa); }
}
