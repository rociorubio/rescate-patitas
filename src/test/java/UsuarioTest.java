import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;

import persona.usuario.Hasheador;
import persona.usuario.TipoUsuario;
import persona.usuario.Usuario;
import validadores.ValidadorContrasenia;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UsuarioTest {

  ValidadorContrasenia validadorContrasenia = new ValidadorContrasenia();
  String contrasenia = "$ricaRRdo241@";
  Usuario usuario = new Usuario("pepe", contrasenia, TipoUsuario.USUARIO_NORMAL, validadorContrasenia);

  @Test
  public void generarNuevoUsuario(){
    assertEquals(usuario.getUsername(), "pepe");
    assertNotEquals(usuario.getPassword(), contrasenia);
  }

}