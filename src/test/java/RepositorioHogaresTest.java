import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;

import org.junit.Assert;
import apiHogares.Hogar;
import apiHogares.ImportadorAPIHogares;
import mascota.*;
import repositorios.RepositorioHogares;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioHogaresTest {

    ImportadorAPIHogares importadorAPIHogares = new ImportadorAPIHogares();
    RepositorioHogares repositorioHogares = new RepositorioHogares(importadorAPIHogares, 1450);

    List<String> fotos = new ArrayList<>();
    List<String> caracteristicas = new ArrayList<>();
    Ubicacion parqueChacabuco = new Ubicacion(17.546, 46.123);
    MascotaPerdida mascotaEncontada1 = new MascotaPerdida(LocalDate.now().minusDays(11),null, fotos,"Peludo",parqueChacabuco, TipoMascota.PERRO , TamanioMascota.GRANDE);


    //Ver que capaz falla cuando se importen los hogares de la api
    @Test
    public void agregarHogarTest(){
        int size = repositorioHogares.getHogares().size();
        Hogar hogar = new Hogar("01","casa1","42241542",4,5,true);
        repositorioHogares.agregarHogar(hogar);

        Assert.assertEquals(size+1,repositorioHogares.getHogares().size());
    }
/*
    @Test
    public void getHogaresAdecuadosTest(){
        Hogar hogar = new Hogar("01","casa1","42241542",4,3,true);
        Hogar hogar2 = new Hogar("02","casa2","42243442",4,3,true);
        caracteristicas.add("Peludo");
        hogar2.setCaracteristicas(caracteristicas);
        hogar.setCaracteristicas(caracteristicas);
        hogar.setUbicacion(new Ubicacion(17.,44.));
        hogar2.setUbicacion(new Ubicacion(12.,30.));

        hogar.setAdmisiones(new Admision(true,false));
        hogar2.setAdmisiones(new Admision(true,false));

        repositorioHogares.agregarHogar(hogar);
        repositorioHogares.agregarHogar(hogar2);

        Assert.assertEquals(1,repositorioHogares.getHogaresAdecuados(mascotaEncontada1).size());
    }


    @Test
    public void getHogaresAdecuadosRadioNegativoTest(){

        Assert.assertThrows(IllegalArgumentException.class,() -> {List<Hogar> hogares = repositorioHogares.getHogaresAdecuados(mascotaEncontada1);});
    }
*/

}
