package exceptions;

public class CaracteristicaExistenteException extends RuntimeException{
    public CaracteristicaExistenteException(String causa) {
        super(causa);
    }
}
