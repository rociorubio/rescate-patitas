package validadores;

public class ValidadorDeLongitud extends Validador {
    private final int longitudMinima;
    private final int longitudMaxima;
    private String descripcion = "La contrasenia debe tener entre "+ getLongitudMinima() +
        " y " + getLongitudMaxima() + " caracteres";

    public ValidadorDeLongitud(int _longitudMinima, int _longitudMaxima){
        this.longitudMinima = _longitudMinima;
        this.longitudMaxima = _longitudMaxima;
    }

    @Override
    public boolean validarContrasenia(String contrasenia) {
        return contrasenia.length() >= this.longitudMinima && contrasenia.length() <= this.longitudMaxima;
    }

    @Override
    public String descripcion(){ return descripcion; }
    public int getLongitudMinima() { return longitudMinima; }
    public int getLongitudMaxima() { return longitudMaxima; }
}
