package repositorios;

import caracteristicas.Caracteristica;
import mascota.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import persona.TipoDocumento;
import repositorios.dao.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioMascotas extends Repositorio implements WithGlobalEntityManager{

    public RepositorioMascotas(Dao<Mascota> dao){
        super(dao);
    }

    public Mascota mascotaEncontradaEstaRegistrada(MascotaPerdida encontrada) {
        List<Mascota> mascotas = buscarTodos();
        List<Mascota> mascotaEncontradisima = mascotas.stream().filter(mascotita -> mascotita.getChapita()
            .equals(encontrada.getChapita())).collect(Collectors.toList());
        return mascotaEncontradisima.get(0);
    }


    public List<MascotaPerdida> getMascotasPerdidas() {
        System.out.println(entityManager()
                .createQuery("from MascotaPerdida", MascotaPerdida.class) //
                .getResultList());
        return entityManager()
                .createQuery("from MascotaPerdida", MascotaPerdida.class) //
                .getResultList();
    }

    public List<Mascota> getMascotasIngresadas() {
        System.out.println(entityManager()
                .createQuery("from Mascota", Mascota.class) //
                .getResultList());
        return entityManager()
                .createQuery("from Mascota", Mascota.class) //
                .getResultList();
    }

    public List<Mascota> buscarPorNombre(String nombre) {
        return entityManager()
            .createQuery("from Mascota m where m.nombre like :nombre", Mascota.class)
            .setParameter("nombre", "%" + nombre + "%") //
            .getResultList();
    }

    public List<Mascota> buscarMascotasPorDuenio(Long idDuenio) {
        List<Mascota> mascotas = getMascotasIngresadas();
        return mascotas.stream().filter(mascotita -> mascotita.getDuenioMascota().getUsuario().getIdUsuario().equals(idDuenio)).collect(Collectors.toList());

    }

}

