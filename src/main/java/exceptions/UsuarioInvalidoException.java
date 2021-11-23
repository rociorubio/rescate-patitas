package exceptions;

public class UsuarioInvalidoException extends RuntimeException{
  public UsuarioInvalidoException(String causa){super(causa);}
}
