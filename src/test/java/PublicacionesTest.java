import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import persona.*;
import persona.usuario.TipoUsuario;
import persona.usuario.Usuario;
import mascota.*;
import publicaciones.*;
import publicaciones.EstadoDePublicacion;
import publicaciones.adopcion.*;
import repositorios.RepositorioMascotas;
import repositorios.RepositorioAsociaciones;
import repositorios.RepositorioPublicacionParaDarEnAdopcion;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;
import validadores.ValidadorContrasenia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PublicacionesTest {
  Dao daoMascotas = new DaoHibernate(Mascota.class);
  RepositorioMascotas repoMascota = new RepositorioMascotas(daoMascotas);

  Dao dao = new DaoHibernate(PublicacionParaDarEnAdopcion.class);
  RepositorioPublicacionParaDarEnAdopcion repositorio = new RepositorioPublicacionParaDarEnAdopcion(dao);

  Dao daoAsociaciones = new DaoHibernate(Asociacion.class);
  RepositorioAsociaciones repoAsociaciones = new RepositorioAsociaciones(daoAsociaciones);

  Persona persona = new Persona("Roberto", "Ramirez", 569874125, "robert@gmail.com", TipoDocumento.DNI,4589632);
  Rescatista rescatista = new Rescatista(persona, new Direccion("Bartolome Mitre", "1458", "1A", "CABA", "Buenos Aires"));
  DuenioMascota roberto = new DuenioMascota(persona, new Usuario("roberto_123", "password1Dzd%", TipoUsuario.USUARIO_NORMAL, new ValidadorContrasenia()) );

  Mascota peluca = new Mascota("Peluca", "Pelu", 3, new ArrayList<>(), TipoMascota.PERRO, SexoMascota.MACHO, "www.google.com.ar", "chapita", roberto);
  MascotaPerdida mascotaEncontrada = new MascotaPerdida(LocalDate.now(), null, new ArrayList<>(),"flaco, con una manchita en el ojo", new Ubicacion(26.,32.), TipoMascota.PERRO, TamanioMascota.MEDIANO);

  PublicacionConChapita publicacionConChapita = new PublicacionConChapita(rescatista, peluca);
  PublicacionSinChapita publicacionSinChapita = new PublicacionSinChapita(rescatista, mascotaEncontrada);

  List<Opcion> opciones1 = Arrays.asList(new Opcion("Si"), new Opcion("No"));
  Pregunta pregunta1 = new Pregunta("¿El perro necesita patio?", "¿Tiene patio la casa?", opciones1);
  Respuesta respuesta1 = new Respuesta(pregunta1);

  List<Opcion> opciones2 = Arrays.asList(new Opcion("Grande"), new Opcion("Mediano") ,new Opcion("Chico"));
  Pregunta pregunta2 = new Pregunta("¿De que tamanio es el perro?", "¿De que tamanio se prefiere el perro?", opciones2);
  Respuesta respuesta2 = new Respuesta(pregunta2);

  Ubicacion ubicacion = new Ubicacion(25.,31.);
  Asociacion asociacion1 = new Asociacion(ubicacion, new ArrayList<>());

  @BeforeEach
  public void init(){
    respuesta1.validar("Si");
    respuesta2.validar("Grande");

    asociacion1.agregarPreguntasParaAdopcion(pregunta1);
    asociacion1.agregarPreguntasParaAdopcion(pregunta2);
  }

  @Test
  public void unaPublicacionConChapitaPuedeSerAprobada() {
    publicacionConChapita.aprobar();
    assertEquals(publicacionConChapita.getEstadoDePublicacion(), EstadoDePublicacion.APROBADO);
  }

  @Test
  public void unaPublicacionConChapitaPuedeSerRechazada() {
    publicacionConChapita.rechazar();
    assertEquals(publicacionConChapita.getEstadoDePublicacion(), EstadoDePublicacion.NO_APROBADO);
  }

  @Test
  public void unaPublicacionConChapitaPuedeRetornarSuRescatista() {
    assertEquals(publicacionConChapita.getRescatista(), rescatista);
  }

  @Test
  public void unaPublicacionSinChapitaPuedeSerAprobada() {
    publicacionSinChapita.aprobar();
    assertEquals(publicacionSinChapita.getEstadoDePublicacion(), EstadoDePublicacion.APROBADO);
  }

  @Test
  public void unaPublicacionSinChapitaPuedeSerRechazada() {
    publicacionSinChapita.rechazar();
    assertEquals(publicacionSinChapita.getEstadoDePublicacion(), EstadoDePublicacion.NO_APROBADO);
  }

  @Test
  public void unaPublicacionSinChapitaPuedeRetornarSuRescatista() {
    assertEquals(publicacionSinChapita.getRescatista(), rescatista);
  }

/*
  @Test
  public void unaPublicacionConChapitaPuedeBuildearseYSeAsignaASuAsociacionCorrespondiente(){
    mascotaEncontrada.setChapita("chapita");
    repoMascota.agregar(peluca);
    repoAsociaciones.agregar(asociacion1);

    BuilderPublicacionMascotaConChapita builderPublicacion = new BuilderPublicacionMascotaConChapita();
    builderPublicacion.rescatista(rescatista);
    builderPublicacion.mascotaEncontrada(mascotaEncontrada);
    builderPublicacion.setRepositorioMascotas(repoMascota);
    builderPublicacion.setRepositorioAsociaciones(repoAsociaciones);
    builderPublicacion.build();

    assertTrue(repo);
  }


  @Test
  public void unaPublicacionParaDarEnAdopcionPuedeGenerarseConElBuilder() {
    preguntaOpcional1.responder("Si");
    BuilderPublicacionParaDarEnAdopcion builderPublicacion = new BuilderPublicacionParaDarEnAdopcion();
    builderPublicacion.setAsociacion(asociacion);
    builderPublicacion.setMascota(peluca);
    PublicacionParaDarEnAdopcion publicacion = builderPublicacion.build();
    assertTrue(asociacion.getPublicacionesParaDarEnAdopcion().contains(publicacion));
  }

  @Test
  public void unaPublicacionParaAdoptarPuedeGenerarseConElBuilder() {
    preguntaOpcional1.responder("Si");
    BuilderPublicacionParaAdoptar builderPublicacion = new BuilderPublicacionParaAdoptar();
    builderPublicacion.setAsociacion(asociacion);
    builderPublicacion.setPersona(robertoP);
    PublicacionParaAdoptar publicacion = builderPublicacion.build();
    assertTrue(asociacion.getPublicacionesParaAdoptar().contains(publicacion));
  }*/
}