package main;

import caracteristicas.*;
import mascota.*;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import persona.*;
import persona.usuario.TipoUsuario;
import persona.usuario.Usuario;
import publicaciones.PublicacionConChapita;
import publicaciones.PublicacionSinChapita;
import publicaciones.adopcion.*;
import validadores.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  public static void main(String[] args) { new Bootstrap().run();}

  public void run() {
    List<Opcion> opciones = Arrays.asList(new Opcion("Grande"), new Opcion("Mediano") ,new Opcion("Chico"));
    Caracteristica caracteristicaConOpciones = new CaracteristicaConOpciones("Tamanio", opciones);
    Atributo atributoConOpciones = new Atributo(caracteristicaConOpciones, "Grande");

    List<Opcion> opcionesCaracteristica2 = Arrays.asList(new Opcion("Largo"), new Opcion("Corto") ,new Opcion("Sin Pelo"));
    Caracteristica caracteristicaConOpciones2 = new CaracteristicaConOpciones("Pelo", opcionesCaracteristica2);
    Atributo atributoConOpciones2 = new Atributo(caracteristicaConOpciones2, "Corto");

    Caracteristica caracteristicaLibre = new CaracteristicaLibre("Color");
    Atributo atributoLibre = new Atributo(caracteristicaLibre, "Marroncito");

    ArrayList<Atributo> atributos = new ArrayList<Atributo>(){{ add(atributoLibre); add(atributoConOpciones);}};

    List<Opcion> opciones1 = Arrays.asList(new Opcion("Si"), new Opcion("No"));
    Pregunta pregunta1 = new Pregunta("¿El perro necesita patio?", "¿Tiene patio la casa?", opciones1);

    Pregunta pregunta2 = new Pregunta("¿De que tamanio es el perro?", "¿De que tamanio se prefiere el perro?", opciones);

    ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>(){{ add(pregunta1); add(pregunta2);}};
    Ubicacion ubicacion = new Ubicacion(20., 30.);
    Asociacion asociacion = new Asociacion(ubicacion, preguntas);

    Persona personaDuenioMascota = new Persona("Roberto", "Garcia", 123456789, "roberto@gmail.com", TipoDocumento.DNI, 54971564);

    Validador validadorLongitud = new ValidadorDeLongitud(8,64);
    Validador validadorContenido = new ValidadorContenido();
    Validador validadorEnBlackList = new ValidadorEnBlackList();
    ValidadorContrasenia validadorContrasenia = new ValidadorContrasenia(validadorLongitud, validadorContenido, validadorEnBlackList);
    Usuario usuario = new Usuario("roberto_1983", "asdaMENA12314", TipoUsuario.USUARIO_NORMAL, validadorContrasenia);
    Usuario usuarioAdmin = new Usuario("rocioadmin", "rocioADMIN1234", TipoUsuario.USUARIO_ADMINISTRADOR, validadorContrasenia);

    DuenioMascota duenioMascota = new DuenioMascota(personaDuenioMascota, usuario);
    Mascota mascota = new Mascota("Peluca", "Pelu", 3, atributos, TipoMascota.GATO, SexoMascota.HEMBRA, new String(), "chapitaNumberOne", duenioMascota);

    Persona personaRescatista = new Persona("Gabriel García", "Márquez", 987654321, "gabriel@gmail.com", TipoDocumento.DNI, 78654321);
    Direccion direccion = new Direccion("Av. Siempre Viva", "742", "-", "Springfield", "Evergreen Terrace");
    Rescatista rescatista = new Rescatista(personaRescatista, direccion);
    MascotaPerdida mascotaPerdida = new MascotaPerdida(LocalDate.now(), null, new ArrayList<>(), "Gordito, tiene una oreja cortada", ubicacion, TipoMascota.PERRO, TamanioMascota.GRANDE);
    PublicacionMascotaPerdida publicacionMascotaPerdida = new PublicacionMascotaPerdida(rescatista, mascotaPerdida);

    MetodoDeContacto metodoDeContacto = new MetodoDeContacto("Micaela", 456123789, "micaela@gmail.com");

    Persona personaDuenioReclamante = new Persona("Marisa", "Reyes", 756324915, "marisa@gmail.com", TipoDocumento.DNI, 45867235);
    DuenioReclamante duenioReclamante = new DuenioReclamante(personaDuenioReclamante, direccion, publicacionMascotaPerdida);

    Persona personaAdopante = new Persona("Maria", "Sanchez", 123789456, "maria@gmail.com", TipoDocumento.DNI, 45678126);
    PersonaAdoptante adoptante = new PersonaAdoptante(personaAdopante);

    Respuesta respuesta1 = new Respuesta(pregunta1);
    Respuesta respuesta2 = new Respuesta(pregunta2);

    List<Respuesta> respuestas = Arrays.asList(respuesta1, respuesta2);

    PublicacionParaAdoptar publicacionParaAdoptar = new PublicacionParaAdoptar(respuestas, personaAdopante);
    PublicacionParaDarEnAdopcion publicacionParaDarEnAdopcion = new PublicacionParaDarEnAdopcion(respuestas, mascota);

    PublicacionConChapita publicacionConChapita = new PublicacionConChapita(rescatista, mascota);
    PublicacionSinChapita publicacionSinChapita = new PublicacionSinChapita( rescatista, mascotaPerdida);


    withTransaction(() -> {
      persist(atributoConOpciones);
      persist(atributoConOpciones2);
      persist(atributoLibre);
      persist(asociacion);
      persist(usuarioAdmin);
      persist(mascota);
      persist(publicacionMascotaPerdida);
      persist(duenioReclamante);
      persist(adoptante);
      persist(publicacionParaAdoptar);
      persist(publicacionParaDarEnAdopcion);
      persist(publicacionConChapita);
      persist(publicacionSinChapita);
    });
  }

}
