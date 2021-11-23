package repositorios;

import mascota.Asociacion;
import mascota.MascotaPerdida;
import repositorios.dao.Dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeMath.min;

public class RepositorioAsociaciones extends Repositorio{

    public RepositorioAsociaciones(Dao<Asociacion> dao){
        super(dao);
    }

    public Asociacion asociacionAcorde(MascotaPerdida mascotaPerdida){
        double distanciaMinima = getDistanciaMínima(mascotaPerdida);
        List<Asociacion> asociaciones = buscarTodos();

        return asociaciones.stream()
                .filter(asociacion -> getDistancia(mascotaPerdida, asociacion) == distanciaMinima)
                .collect(Collectors.toList())
                .get(0); // Se podría tomar cualquiera de las Asociaciones que estan a esa misma distancia minima.
    }

    public double getDistanciaMínima(MascotaPerdida mascotaPerdida){
        List<Asociacion> asociaciones = buscarTodos();
        return Collections.min(asociaciones.stream().map(asociacion -> getDistancia(mascotaPerdida, asociacion))
                .collect(Collectors.toList()));
    }

    public double getDistancia(MascotaPerdida mascotaPerdida, Asociacion asociacion){
        return asociacion.getUbicacionAsociacion().distance(mascotaPerdida.getUbicacionMascota());
    }

}
