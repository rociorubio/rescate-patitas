package repositorios;

import caracteristicas.Caracteristica;
import caracteristicas.CaracteristicaConOpciones;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import publicaciones.adopcion.Opcion;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioOpciones extends Repositorio implements WithGlobalEntityManager {

	public RepositorioOpciones(Dao<Caracteristica> dao) {
		super(dao);
	}

	public List<Opcion> buscarPorNombre(String nombre) {
		return entityManager()
				.createQuery("from Opcion o where o.opcion like :nombre", Opcion.class)
				.setParameter("nombre", "%" + nombre + "%") //
				.getResultList();
	}

	public List<Opcion> filtrarPorCaracteristica(String idCaracteristica) {

		Dao daoC = new DaoHibernate(Caracteristica.class);
		RepositorioCaracteristicas repo = new RepositorioCaracteristicas(daoC);
		Caracteristica caracteristica = (Caracteristica) repo.buscar(Long.parseLong(idCaracteristica));

		return caracteristica.getOpciones();
	}
}
