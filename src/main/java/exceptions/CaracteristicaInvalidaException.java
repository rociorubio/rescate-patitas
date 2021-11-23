package exceptions;

public class CaracteristicaInvalidaException extends RuntimeException{
    public CaracteristicaInvalidaException(String causa) {
        super(causa);
    }
}
