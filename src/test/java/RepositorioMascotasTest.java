//import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import mascota.TamanioMascota;
import persona.usuario.TipoUsuario;
import persona.usuario.Usuario;
import publicaciones.adopcion.Pregunta;

		import persona.*;
import mascota.*;
import mensajeria.*;
import mascota.MascotaPerdida;
		import repositorios.RepositorioMascotas;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;
import validadores.ValidadorContrasenia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioMascotasTest {
    Dao dao = new DaoHibernate<>(Mascota.class);
    RepositorioMascotas repositorioMascotas = new RepositorioMascotas(dao);

    Persona robertoP = new Persona("Roberto", "Ramirez", 569874125, "robert@gmail.com", TipoDocumento.DNI,4589632);
    DuenioMascota duenioGenerico = new DuenioMascota(robertoP, new Usuario("roberto_123", "password1Dzd%", TipoUsuario.USUARIO_NORMAL, new ValidadorContrasenia()));
    Mascota mascota1 = new Mascota("Malena", "Male", 11, new ArrayList<>(), TipoMascota.PERRO, SexoMascota.MACHO, "C:/Temp/", "1", duenioGenerico);
    Mascota mascota2 = new Mascota("Willy", "Wi", 9, new ArrayList<>(), TipoMascota.GATO, SexoMascota.MACHO, "C:/Temp/", "2", duenioGenerico);
    Mascota mascota3 = new Mascota("Lazy", "Estrellita", 3, new ArrayList<>(), TipoMascota.GATO, SexoMascota.MACHO, "C:/Temp/", "3", duenioGenerico);

    Direccion direccionRescatista = new Direccion("Calle", "altura", "piso", "localidad", "provincia");

    Ubicacion parqueChacabuco = new Ubicacion(17.546, 46.123);
    Ubicacion parquePatricios = new Ubicacion(100.40, 200.04);

    List<String> fotos = new ArrayList<>();
    JavaMail javaMail = new JavaMail();

    MascotaPerdida mascotaEncontada1 = new MascotaPerdida(LocalDate.now().minusDays(11), null, fotos, "Peludo", parqueChacabuco, TipoMascota.PERRO, TamanioMascota.CHICO);
    MascotaPerdida mascotaEncontada2 = new MascotaPerdida(LocalDate.now().minusDays(9), null, fotos, "Castrado", parquePatricios, TipoMascota.PERRO, TamanioMascota.CHICO);

    List<Mascota> listaTest = new ArrayList<>();
    List<Pregunta> preguntasObligatorias = new ArrayList<>();
    List<Pregunta> preguntasOpcionales = new ArrayList<>();
    Asociacion asociacion = new Asociacion(parqueChacabuco, new ArrayList<>());

    @BeforeEach
    void init() {
        fotos.add("https://i.pinimg.com/originals/b0/15/c9/b015c935658f49afcbfeb1b50428e1eb.jpg");
        repositorioMascotas.agregar(mascota1);
        repositorioMascotas.agregar(mascota2);
        repositorioMascotas.agregar(mascota3);
        listaTest.add(mascota3);
        mascotaEncontada1.setChapita("1");
        mascotaEncontada2.setChapita("3");

    }
}