package exceptions;

public class PersonaInvalidaException extends RuntimeException{
  public PersonaInvalidaException(String causa) {
    super(causa);
  }
}
