import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import persona.usuario.*;
import caracteristicas.*;
import exceptions.AtributoInvalidoException;
import exceptions.CaracteristicaExistenteException;
import mascota.*;
import persona.*;
import publicaciones.adopcion.Opcion;
import repositorios.RepositorioCaracteristicas;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;
import validadores.ValidadorContrasenia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class CaracteristicaTest {
  Dao dao = new DaoHibernate(Caracteristica.class);
  RepositorioCaracteristicas repo = new RepositorioCaracteristicas(dao);

  Caracteristica castrado = new CaracteristicaLibre("Castrado");
  Opcion opcion = new Opcion("Marron");
  Opcion opcion2 = new Opcion("Amarillo");
  Opcion opcion3 = new Opcion("Negro");

  Caracteristica color = new CaracteristicaConOpciones("Color", setOpciones(opcion,opcion2,opcion3));
  Persona robertoP = new Persona("Roberto", "Ramirez", 569874125, "robert@gmail.com", TipoDocumento.DNI,4589632);
  DuenioMascota roberto = new DuenioMascota(robertoP, new Usuario("Pablo", "password",  TipoUsuario.USUARIO_NORMAL, new ValidadorContrasenia()));
  Mascota peluca = new Mascota("Peluca", "Pelu", 3, new ArrayList<>(), TipoMascota.PERRO, SexoMascota.MACHO, "www.google.com.ar", "www.google.com.ar", roberto);
  Mascota toto = new Mascota("Toto", "Totito", 5, new ArrayList<>(), TipoMascota.PERRO, SexoMascota.MACHO, "www.google.com.ar", "www.google.com.ar", roberto);

  Atributo atributoCastrado = new Atributo(castrado, "Esta Castrado");
  Atributo atributoColor = new Atributo(color, "Negro");

  @BeforeEach
  void init(){
    peluca.setAtributos(new ArrayList<>());
  }

  @Test
  public void sePuedeAgregarUnaCaracteristicaLibre(){
    toto.agregarAtributo(atributoCastrado);
    assertTrue(toto.getAtributos().contains(atributoCastrado));
    assertEquals(toto.getAtributos().get(0).getValor(), "Esta Castrado");
    assertEquals(toto.getAtributos().get(0).getCaracteristica(), castrado);
    assertEquals(toto.getAtributos().get(0).getCaracteristica().getNombre(), "Castrado");
  }

  @Test
  public void sePuedeAgregarUnaCaracteristicaConOpciones(){
    peluca.agregarAtributo(atributoColor);
    assertTrue(peluca.getAtributos().contains(atributoColor));
    assertEquals(peluca.getAtributos().get(0).getCaracteristica().getNombre(),"Color");
  }

  @Test
  public void unAtributoEsInvalidoSiSuDescripcionNoEstaEnLaListaDeOpcionesDeLaCaracteristica(){
    assertThrows(AtributoInvalidoException.class, () -> {
      Atributo atributoColor = new Atributo(color, "Azul");
      peluca.agregarAtributo(atributoColor);
      assertTrue(peluca.getAtributos().contains(atributoColor));

    });
  }

  @Test
  public void sePuedenCargarCaracteristicasEnElRepo(){
    repo.agregarCaracteristica(color);
    repo.agregarCaracteristica(castrado);
    assertTrue(repo.buscarTodos().contains(castrado));
    assertTrue(repo.buscarTodos().contains(color));
  }

  @Test
  public void noSePuedenCargarDosCaracteristicasIguales(){
    assertThrows(CaracteristicaExistenteException.class, () -> {
      repo.agregarCaracteristica(castrado);
      repo.agregarCaracteristica(castrado);
      repo.agregarCaracteristica(castrado);
    });
  }

  public List<Opcion> setOpciones(Opcion opcion, Opcion opcion2, Opcion opcion3){

    List<Opcion> opciones = new ArrayList<>();
    opciones.add(opcion);
    opciones.add(opcion2);
    opciones.add(opcion3);

    return opciones;
  }

}