import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import repositorios.RepositorioHogares;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import apiHogares.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class APIHogaresTest {

    HogaresRestClient APIHogares = new HogaresRestClient();
    ImportadorAPIHogares importadorAPIHogares = new ImportadorAPIHogares();
    RepositorioHogares repositorioHogares = new RepositorioHogares(importadorAPIHogares, 500);
    Client client = new Client();

    @BeforeEach
    public void setUp() {
        APIHogares = new HogaresRestClient();
        importadorAPIHogares = new ImportadorAPIHogares();
    }

    @Test
    public void UnaConexionConLaAPIDaCodigo200(){
        ClientResponse respuesta = APIHogares.getHogares("1",client);
        assertEquals(respuesta.getStatus(), 200);
    }

    @Test
    public void UnGETSinOffsetDaError400(){
        ClientResponse respuesta = APIHogares.getHogares("",client);
        assertEquals(respuesta.getStatus(), 400);
    }

    @Test
    public void UnGETConOffsetErroneoDaErrorDaError400(){
        ClientResponse respuesta = APIHogares.getHogares("-1",client);
        assertEquals(respuesta.getStatus(), 400);
    }

    @Test
    public void PruebaDeIntegridadDelQuitoHogar(){
        Hogar hogarIntegro = repositorioHogares.getHogares().get(4);

        assertEquals(hogarIntegro.getNombre(), "El campito Refugio");
        assertEquals(hogarIntegro.getUbicacion().getLatitud(), -34.891156274674614, 0);
        assertEquals(hogarIntegro.getUbicacion().getLongitud(), -58.43278121166188, 0);
        assertEquals(hogarIntegro.getTelefono(), "+541123965480");
        assertEquals(hogarIntegro.getAdmisiones().isPerros(), true);
        assertEquals(hogarIntegro.getAdmisiones().isGatos(), true);
        assertEquals(hogarIntegro.getCapacidad(),180);
        assertEquals(hogarIntegro.getLugaresDisponibles(), 80);
        assertEquals(hogarIntegro.getPatio(), true);
        assertEquals(hogarIntegro.getCaracteristicas(), new ArrayList<String>());

    }
}
