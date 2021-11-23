import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;

import org.junit.Assert;
import mascota.Asociacion;
import mascota.Mascota;
import mascota.Ubicacion;
import mascota.TipoMascota;
import mascota.SexoMascota;
import persona.*;
import persona.usuario.TipoUsuario;
import persona.usuario.Usuario;
import publicaciones.adopcion.Pregunta;
import repositorios.RepositorioAsociaciones;
import repositorios.RepositorioCaracteristicas;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;
import validadores.ValidadorContrasenia;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAsociacionesTest {
    Dao dao = new DaoHibernate(Asociacion.class);
    RepositorioAsociaciones repositorio = new RepositorioAsociaciones(dao);

    Ubicacion parqueChacabuco = new Ubicacion(17.546, 46.123);
    Persona robertoP = new Persona("Roberto", "Ramirez", 569874125, "robert@gmail.com", TipoDocumento.DNI,4589632);
    DuenioMascota duenioGenerico = new DuenioMascota(robertoP, new Usuario("roberto_123", "password1Dzd%", TipoUsuario.USUARIO_NORMAL, new ValidadorContrasenia()));
    Mascota mascota1 = new Mascota("Malena", "Male", 11, new ArrayList<>(), TipoMascota.PERRO, SexoMascota.MACHO, "C:/Temp/", "1", duenioGenerico);
    List<String> fotos = new ArrayList<>();
    List<Pregunta> preguntasObligatorias = new ArrayList<>();
    List<Pregunta> preguntasOpcionales = new ArrayList<>();

    //Test listar publicaciones de los ultimos 10 dias

    @Test
    public void sePuedeAgregarUnaAsociacionAlRepositorioDeAsociaciones(){

        Asociacion asociacion = new Asociacion(parqueChacabuco, preguntasObligatorias);


        repositorio.agregar(asociacion);
        Assert.assertEquals(asociacion,repositorio.buscar(asociacion.getIdAsociacion()));
    }


}
