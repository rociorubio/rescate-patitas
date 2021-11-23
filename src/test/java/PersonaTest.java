import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.*;
import persona.*;
import persona.usuario.*;
import org.mockito.Mockito;
import validadores.ValidadorContrasenia;

import static org.mockito.Mockito.*;

public class PersonaTest {
  Persona robertoP = new Persona("Roberto", "Ramirez", 569874125, "robert@gmail.com", TipoDocumento.DNI,4589632);
  DuenioMascota ricardo = new DuenioMascota(robertoP, new Usuario("roberto_123", "password1Dzd%", TipoUsuario.USUARIO_NORMAL, new ValidadorContrasenia()));

  MetodoDeContacto contactoJose = new ContactoMail("Jose", 15346546,"jose@mail.com");
  MetodoDeContacto contactoJuan = new ContactoSms("Juan", 23456789, "juanchi@gmail.com");

  @Test
  public void duenioMascotaPuedeTenerMasDeUnContacto(){
    ricardo.agregarContacto(contactoJose);
    ricardo.agregarContacto(contactoJuan);
    assertEquals(ricardo.getContactos().get(0).getNombreApellido(), "Jose");
    assertEquals(ricardo.getContactos().get(1).getTelefono(), 23456789);
    assertEquals(ricardo.getContactos().size(), 2);
  }

  @Test
  public void atributosPersonaTest(){

    Assert.assertEquals("Roberto",robertoP.getNombre());
    Assert.assertEquals("Ramirez",robertoP.getApellido());
    Assert.assertEquals("robert@gmail.com",robertoP.getMail());
    Assert.assertEquals(TipoDocumento.DNI,robertoP.getTipoDocumento());

  }

  @Test
  public void notificarPersonaTest(){

    MetodoDeContacto contacto = mock(MetodoDeContacto.class);
    ricardo.agregarContacto(contacto);
    Mockito.doNothing().when(contacto).notificar(Mockito.any());
    ricardo.notificar("Algo");

    Mockito.verify(contacto,times(1)).notificar("Algo");
  }

  @Test
  public void notificarPersonaTestSMS(){
    ContactoSms contacto = mock(ContactoSms.class);
    ricardo.agregarContacto(contacto);
    Mockito.doNothing().when(contacto).notificar(Mockito.any());
    ricardo.notificar("Algo");

    Mockito.verify(contacto,times(1)).notificar("Algo");
  }

  @Test
  public void notificarPersonaTestMail(){
    ContactoMail contacto = mock(ContactoMail.class);
    ricardo.agregarContacto(contacto);
    Mockito.doNothing().when(contacto).notificar(Mockito.any());
    ricardo.notificar("Algo");

    Mockito.verify(contacto,times(1)).notificar("Algo");
  }
}