package repositorios;


import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import caracteristicas.Caracteristica;
import exceptions.CaracteristicaExistenteException;
import exceptions.CaracteristicaInvalidaException;
import repositorios.dao.Dao;

public class RepositorioCaracteristicas extends Repositorio implements WithGlobalEntityManager{
    
    public RepositorioCaracteristicas(Dao<Caracteristica> dao) {
        super(dao);
    }

    public void agregarCaracteristica(Caracteristica caracteristica) {
        if(caracteristica == null) throw new CaracteristicaInvalidaException("Caracteristica inválida");
        List<Caracteristica> caracteristicas = buscarTodos();

        if (caracteristicas.contains(caracteristica))
            throw new CaracteristicaExistenteException("La caracteristica ingresada ya existe, seleccionela directamente");
        agregar(caracteristica);
    }

    public List<Caracteristica> buscarPorNombre(String nombre) {
        return entityManager()
            .createQuery("from Caracteristica c where c.nombre like :nombre", Caracteristica.class)
            .setParameter("nombre", "%" + nombre + "%") //
            .getResultList();
    }

}






