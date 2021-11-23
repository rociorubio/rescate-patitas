package validadores;

import java.util.List;
import java.util.stream.Collectors;

public class ValidadorContenido extends Validador {
    private String descripcion = "La contrasenia no es lo suficientemente compleja. Debe tener al menos un numero, una mayuscula y una minuscula.";

    @Override
    public boolean validarContrasenia(String contrasenia) {
        List<Character> listaCaracteres = contrasenia.chars().mapToObj(caracter -> (char) caracter).collect(Collectors.toList());
        return (
            this.contieneMayuscula(listaCaracteres) &&
            this.contieneMinuscula(listaCaracteres) &&
            this.contieneNumero(listaCaracteres)
        );
    }

    private boolean contieneMinuscula(List<Character> listaCaracteres){ return listaCaracteres.stream().anyMatch(caracter->Character.isLowerCase(caracter)); }
    private boolean contieneMayuscula(List<Character> listaCaracteres){ return listaCaracteres.stream().anyMatch(caracter->Character.isUpperCase(caracter)); }
    private boolean contieneNumero(List<Character> listaCaracteres) { return listaCaracteres.stream().anyMatch(caracter -> Character.isDigit(caracter)); }

    @Override
    public String descripcion(){ return descripcion; }
}
