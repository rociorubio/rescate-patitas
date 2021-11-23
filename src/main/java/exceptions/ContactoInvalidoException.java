package exceptions;

public class ContactoInvalidoException extends RuntimeException{
  public ContactoInvalidoException(String causa) {
    super(causa);
  }
}
