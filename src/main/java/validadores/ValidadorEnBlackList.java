package validadores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ValidadorEnBlackList extends Validador {

    private String blacklist = "src/recursos/blacklist.txt";
    private String descripcion = "La contrasenia esta en el TOP 10000 de las peores contrasenias.";

    @Override
    public boolean validarContrasenia(String contrasenia) {
        boolean esValida;

        try (Stream<String> lasPeoresContrasenias = Files.lines(Paths.get(blacklist))) {
            esValida = lasPeoresContrasenias.anyMatch(malaContrasenia -> contrasenia.toLowerCase().equals(malaContrasenia));
        } catch (IOException exception) { throw new RuntimeException("No se encontro archivo: " + blacklist); }

        return !esValida;
    }

    @Override
    public String descripcion(){ return descripcion; }
    public void setBlacklist(String blacklist){this.blacklist = blacklist;}
}
