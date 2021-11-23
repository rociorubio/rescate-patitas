package exceptions;

public class EnvioRechazadoException extends RuntimeException{
    public EnvioRechazadoException(String causa) {
        super(causa);
    }
}
