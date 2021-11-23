import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import persona.usuario.TipoUsuario;
import persona.usuario.Usuario;
import mascota.*;
import persona.*;
import repositorios.RepositorioMascotas;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;
import validadores.ValidadorContrasenia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MascotaTest {
  Dao dao = new DaoHibernate(Mascota.class);
  RepositorioMascotas repositorioMascotas = new RepositorioMascotas(dao);

  Persona robertoP = new Persona("Roberto", "Ramirez", 569874125, "robert@gmail.com", TipoDocumento.DNI,4589632);
  DuenioMascota roberto = new DuenioMascota(robertoP, new Usuario("roberto_123", "password1Dzd%", TipoUsuario.USUARIO_NORMAL, new ValidadorContrasenia()));

  Mascota peluca = new Mascota("Peluca", "Pelu", 3, new ArrayList<>(), TipoMascota.PERRO, SexoMascota.MACHO, "www.google.com.ar", "www.google.com.ar", roberto);
  Mascota toto = new Mascota("Toto", "Toto", 5, new ArrayList<>(), TipoMascota.GATO, SexoMascota.HEMBRA, "/", "/", roberto);
  Ubicacion ubicacion = new Ubicacion(24., 32.);

  List<String> fotos = new ArrayList<>();
  MascotaPerdida mascotaPerdida1 = new MascotaPerdida(LocalDate.now(), null, fotos, "UnaDescripcion", ubicacion, TipoMascota.PERRO, mascota.TamanioMascota.CHICO);

  public void init(){
    fotos.add("foto1");
    fotos.add("foto2");
  }

  @Test
  public void unDuenioPuedeTenerMasDeUnaMascota() {
    assertEquals(peluca.getDuenioMascota(), toto.getDuenioMascota());
  }

  @Test
  public void sePuedenRegistrarMascotasAlRepo(){
    repositorioMascotas.agregar(peluca);
    repositorioMascotas.agregar(toto);
    assertTrue(repositorioMascotas.buscarTodos().contains(peluca));
    assertTrue(repositorioMascotas.buscarTodos().contains(toto));
  }

  //Test para que los GET no quiten coverage
  @Test
  public void integridadDeDatosDeMascota() {
    assertEquals(peluca.getNombre(), "Peluca");
    assertEquals(peluca.getApodo(), "Pelu");
    assertEquals(peluca.getEdad(), 3);
    assertEquals(peluca.getSexo(), SexoMascota.MACHO);
  }

  @Test
  public void integridadDeDatosDeMascotaPerdida(){
    assertEquals(mascotaPerdida1.getLatitud(), 24.);
    assertEquals(mascotaPerdida1.getLongitud(), 32.);
    assertEquals(mascotaPerdida1.getFoto(), fotos);
    assertFalse(mascotaPerdida1.esGato());
    assertTrue(mascotaPerdida1.esPerro());
    mascotaPerdida1.getFecha();
    assertEquals(mascotaPerdida1.getTamanioMascota(), TamanioMascota.CHICO);
    assertEquals(mascotaPerdida1.getTipoMascota(), TipoMascota.PERRO);
  }
}