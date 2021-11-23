import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import mascota.*;
import persona.*;
import publicaciones.EstadoDePublicacion;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuenioReclamanteTest {
  List<String> fotos = new ArrayList<>();
  MascotaPerdida mascotaPerdida = new MascotaPerdida(LocalDate.now().minusDays(11), null, fotos, "Chico", new Ubicacion(1256987.,4687434.), TipoMascota.PERRO, TamanioMascota.CHICO);
  Persona pedroP = new Persona("Pedro", "DelMoro", 48963251, "pedro.moro@gmail.com", TipoDocumento.DNI, 28635987);
  Rescatista rescatista = new Rescatista(pedroP, new Direccion("Aguirre", "2745", "9E", "CABA", "Buenos Aires"));
  PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida(rescatista, mascotaPerdida);
  Direccion direccion = new Direccion("Rivadavia", "2569", "", "Buenos Aires", "Buenos Aires");
  Persona santiagoP = new Persona("Santiago", "Garcia", 52369875, "ro.rubio95@gmail.com", TipoDocumento.DNI, 35896472);
  DuenioReclamante duenioReclamante = new DuenioReclamante(santiagoP, direccion, publicacion);

  @BeforeEach
  void init() {
    fotos.add("https://i.pinimg.com/originals/b0/15/c9/b015c935658f49afcbfeb1b50428e1eb.jpg");
  }

  @Test
  public void duenioReclamanteCompletaFormularioYSeAvisaAlRescatista() throws Exception {
    duenioReclamante.notificacionRescatistaDuenioReclamaMascota();
    assertEquals(publicacion.getEstadoDePublicacion(), EstadoDePublicacion.RESUELTO);
  }

  @Test
  public void duenioReclamanteGetDomicilio(){
    assertEquals(duenioReclamante.getDireccion(), direccion);
  }

  @Test
  public void sePuedeCambiarElEstadoDeUnaPublicacionDeMascotaPerdida(){
    List<String> fotos = new ArrayList<>();
    MascotaPerdida mascotaPerdida = new MascotaPerdida(LocalDate.now().minusDays(11), null, fotos, "Chico", new Ubicacion(1256987.,4687434.), TipoMascota.PERRO, TamanioMascota.CHICO);
    Persona pedroP = new Persona("Pedro", "DelMoro", 48963251, "pedro.moro@gmail.com", TipoDocumento.DNI, 28635987);
    Rescatista rescatista = new Rescatista(pedroP, new Direccion("Aguirre", "2745", "9E", "CABA", "Buenos Aires"));
    PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida(rescatista, mascotaPerdida);

    publicacion.aprobar();
    assertEquals(publicacion.getEstadoDePublicacion(), EstadoDePublicacion.APROBADO);
    publicacion.rechazar();
    assertEquals(publicacion.getEstadoDePublicacion(), EstadoDePublicacion.NO_APROBADO);
    publicacion.pendiente();
    assertEquals(publicacion.getEstadoDePublicacion(), EstadoDePublicacion.PENDIENTE_REVISION);
  }

}