import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import persona.*;
import persona.usuario.TipoUsuario;
import persona.usuario.Usuario;
import scheduler.Scheduler;
import validadores.ValidadorContrasenia;
import mascota.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import publicaciones.adopcion.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SchedulerTest {
  GeneradorDeRecomendaciones generadorDeRecomendaciones = new GeneradorDeRecomendaciones();

  List<Opcion> opciones1 = Arrays.asList(new Opcion("Si"), new Opcion("No"));
  Pregunta pregunta1 = new Pregunta("¿Necesita patio?", "¿Tiene patio?", opciones1);
  Respuesta respuesta1 = new Respuesta(pregunta1);

  List<Opcion> opciones2 = Arrays.asList(new Opcion("Grande"), new Opcion("Mediano"), new Opcion("Chico"));
  Pregunta pregunta2 = new Pregunta("¿Que tamanio tiene?", "¿Que tamanio busca?", opciones2);
  Respuesta respuesta2 = new Respuesta(pregunta2);

  List<Opcion> opciones3 = Arrays.asList(new Opcion("Si"), new Opcion("No"));
  Pregunta pregunta3 = new Pregunta("¿Se adapta con otros animales?", "¿Tiene otros animales?", opciones3);
  Respuesta respuesta3 = new Respuesta(pregunta3);

  Ubicacion ubicacion = new Ubicacion(12.,34.);
  Asociacion asociacion = new Asociacion(ubicacion, new ArrayList<>());

  ContactoMail contactoMail = new ContactoMail("Jose", 123456, "unmail@unmil.com");
  ContactoSms contactoSms = new ContactoSms("Jose", 123456, "unmail@unmil.com");
  Persona robertoP = new Persona("Roberto", "Ramirez", 569874125, "robert@gmail.com", TipoDocumento.DNI,4589632);
  DuenioMascota roberto = new DuenioMascota(robertoP, new Usuario("roberto_123", "password1Dzd%", TipoUsuario.USUARIO_NORMAL, new ValidadorContrasenia()));
  Mascota peluca = new Mascota("Peluca", "Pelu", 3, new ArrayList<>(), TipoMascota.PERRO, SexoMascota.MACHO, "www.google.com.ar", "www.google.com.ar", roberto);

  PublicacionParaAdoptar publicacionParaAdoptar1 = new PublicacionParaAdoptar(new ArrayList<>(), robertoP);
  PublicacionParaDarEnAdopcion publicacionParaDarEnAdopcion1 = new PublicacionParaDarEnAdopcion(new ArrayList<>(), peluca);
  PublicacionParaAdoptar publicacionParaAdoptar2 = new PublicacionParaAdoptar(new ArrayList<>(), robertoP);
  PublicacionParaDarEnAdopcion publicacionParaDarEnAdopcion2 = new PublicacionParaDarEnAdopcion(new ArrayList<>(), peluca);
  PublicacionParaAdoptar publicacionParaAdoptar3 = new PublicacionParaAdoptar(new ArrayList<>(), robertoP);
  PublicacionParaDarEnAdopcion publicacionParaDarEnAdopcion3 = new PublicacionParaDarEnAdopcion(new ArrayList<>(), peluca);


  @BeforeEach
  public void beforeEach(){
    generadorDeRecomendaciones = new GeneradorDeRecomendaciones();
    asociacion = new Asociacion(ubicacion, new ArrayList<>());
    publicacionParaAdoptar1 = new PublicacionParaAdoptar(new ArrayList<>(), robertoP);
    publicacionParaDarEnAdopcion1 = new PublicacionParaDarEnAdopcion(new ArrayList<>(), peluca);
    publicacionParaAdoptar2 = new PublicacionParaAdoptar(new ArrayList<>(), robertoP);
    publicacionParaDarEnAdopcion2 = new PublicacionParaDarEnAdopcion(new ArrayList<>(), peluca);
    publicacionParaAdoptar3 = new PublicacionParaAdoptar(new ArrayList<>(), robertoP);
    publicacionParaDarEnAdopcion3 = new PublicacionParaDarEnAdopcion(new ArrayList<>(), peluca);
  }

  @Test
  public void SchedulerEjecuta() throws Exception {
    Scheduler scheduler = mock(Scheduler.class);
    scheduler.main(null);

    verify(scheduler, times(1)).main(null);
  }

  @Test
  public void SeGeneranRecomendacionesConUnaRespuesta(){
    respuesta1.validar("Si");
    publicacionParaAdoptar1.agregarRespuesta(respuesta1);
    publicacionParaDarEnAdopcion1.agregarRespuesta(respuesta1);

    asociacion.agregarPublicacionParaAdoptar(publicacionParaAdoptar1);
    asociacion.agregarPublicacionParaDarEnAdopcion(publicacionParaDarEnAdopcion1);

    List<Recomendacion> recomendaciones = generadorDeRecomendaciones.generarRecomendaciones(asociacion);
    assertEquals(recomendaciones.size(), 1);
  }


  @Test
  public void SeGeneranRecomendacionesConVariasRespuestas(){
    respuesta1.validar("Si");
    respuesta2.validar("Mediano");
    publicacionParaAdoptar1.agregarRespuesta(respuesta1);
    publicacionParaAdoptar1.agregarRespuesta(respuesta2);
    publicacionParaDarEnAdopcion1.agregarRespuesta(respuesta1);
    publicacionParaDarEnAdopcion1.agregarRespuesta(respuesta2);

    asociacion.agregarPublicacionParaAdoptar(publicacionParaAdoptar1);
    asociacion.agregarPublicacionParaDarEnAdopcion(publicacionParaDarEnAdopcion1);

    List<Recomendacion> recomendaciones = generadorDeRecomendaciones.generarRecomendaciones(asociacion);
    assertEquals(recomendaciones.size(), 1);
  }

  @Test
  public void SeGeneranRecomendacionesConVariasRespuestasAunqueUnaPublicacionTengaMenosRespuestasQueLaOTra(){
    respuesta1.validar("Si");
    publicacionParaAdoptar1.agregarRespuesta(respuesta1);
    publicacionParaDarEnAdopcion1.agregarRespuesta(respuesta1);
    publicacionParaAdoptar1.agregarRespuesta(respuesta2);

    asociacion.agregarPublicacionParaAdoptar(publicacionParaAdoptar1);
    asociacion.agregarPublicacionParaDarEnAdopcion(publicacionParaDarEnAdopcion1);

    List<Recomendacion> recomendaciones = generadorDeRecomendaciones.generarRecomendaciones(asociacion);
    assertEquals(recomendaciones.size(), 1);
  }

  @Test
  public void SeGeneranRecomendacionesConVariasRespuestasYVariasPublicaciones(){
    respuesta1.validar("Si");
    respuesta2.validar("Grande");
    respuesta3.validar("No");

    publicacionParaAdoptar1.agregarRespuesta(respuesta1);
    publicacionParaDarEnAdopcion1.agregarRespuesta(respuesta1);

    publicacionParaAdoptar2.agregarRespuesta(respuesta2);
    publicacionParaDarEnAdopcion2.agregarRespuesta(respuesta2);

    publicacionParaAdoptar3.agregarRespuesta(respuesta3);
    publicacionParaDarEnAdopcion3.agregarRespuesta(respuesta3);

    asociacion.agregarPublicacionParaAdoptar(publicacionParaAdoptar1);
    asociacion.agregarPublicacionParaDarEnAdopcion(publicacionParaDarEnAdopcion1);
    asociacion.agregarPublicacionParaAdoptar(publicacionParaAdoptar2);
    asociacion.agregarPublicacionParaDarEnAdopcion(publicacionParaDarEnAdopcion2);
    asociacion.agregarPublicacionParaAdoptar(publicacionParaAdoptar3);
    asociacion.agregarPublicacionParaDarEnAdopcion(publicacionParaDarEnAdopcion3);

    List<Recomendacion> recomendaciones = generadorDeRecomendaciones.generarRecomendaciones(asociacion);
    assertEquals(recomendaciones.size(), 3);
  }

  @Test
  public void TestDelMensajeAlEnviarRecomendaciones(){
    //TODO Mockear
    respuesta1.validar("Si");
    publicacionParaAdoptar1.agregarRespuesta(respuesta1);
    publicacionParaDarEnAdopcion1.agregarRespuesta(respuesta1);

    robertoP.agregarContacto(contactoMail);
    robertoP.agregarContacto(contactoSms);

    List<Recomendacion> recomendaciones = generadorDeRecomendaciones.generarRecomendaciones(asociacion);


  }
}
