import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;

import caracteristicas.*;
import exceptions.*;
import mascota.*;
import persona.*;
import persona.usuario.*;
import validadores.ValidadorContrasenia;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExceptionsTest {
  Persona robertoP = new Persona("Roberto", "Ramirez", 569874125, "robert@gmail.com", TipoDocumento.DNI,4589632);
  DuenioMascota roberto = new DuenioMascota(robertoP, new Usuario("roberto_123","password1Dzd%", TipoUsuario.USUARIO_NORMAL, new ValidadorContrasenia()));

  //CARACTERISTICA
  @Test
  public void noPuedenCargarseCaracteristicasInvalidas(){
    assertThrows(CaracteristicaInvalidaException.class, () -> {
      Caracteristica caracteristicaErronea = new CaracteristicaConOpciones("ABC", null);
    });
  }

  //MASCOTA
  @Test
  public void noSePuedeCrearUnaMascotaInvalida() {
    assertThrows(MascotaInvalidaException.class, ()  -> {
      Mascota invalida = new Mascota(null, "Peluca", 2, new ArrayList<>(), TipoMascota.GATO, null, "/", "/", roberto);
    });
  }

  //PERSONA

  @Test
  void noSePuedeCrearUnaPersonaInvalida(){
    assertThrows(PersonaInvalidaException.class, () ->{
      Persona p = new Persona("PersonaErronea", "Apellido", null, "mail", TipoDocumento.DNI, null);
    });
  }

  @Test
  void noSePuedeCrearUnRescatistaInvalido(){
    assertThrows(RescatistaInvalidoException.class, () ->{
      Rescatista r = new Rescatista(robertoP,null);
    });
  }

  @Test
  void noSePuedeCrearUnDuenioMascotaInvalido(){
    assertThrows(DuenioMascotaException.class, () ->{
      DuenioMascota d = new DuenioMascota(robertoP, null);
    });
  }

  @Test
  public void noSePuedeCrearUnContactoInvalid(){
    assertThrows(ContactoInvalidoException.class, () ->{
      ContactoMail c = new ContactoMail(null, 45321, "unMail@gmol.uk");
    });
  }

  @Test
  public void testUsuarioThrowsUsuarioInvalidoException() {
    assertThrows(UsuarioInvalidoException.class, () -> {
      Usuario invalido = new Usuario("prueba", null, TipoUsuario.USUARIO_NORMAL, new ValidadorContrasenia());
    });
  }

  @Test
  public void unaDirecciónTieneQueTenerTodosLosDatosCorrectos(){
    assertThrows(DireccionException.class, () -> {
      Direccion direccion = new Direccion("unaCalle", null, "unPiso", "unaLocalidad", "unaProvincia");
    });
  }

  @Test
  public void unaMascotaPerdidaTieneQueTenerTodosLosDatosCorrectos() {
    assertThrows(MascotaPerdidaInvalidaException.class, () -> {
      MascotaPerdida mascotaPerdida = new MascotaPerdida(null, null,null, null, null, null, null);
    });
  }
}
