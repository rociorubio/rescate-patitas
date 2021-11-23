package repositorios;

import db.EntityManagerHelper;
import repositorios.dao.Dao;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class Repositorio<T> {
	protected Dao<T> dao;

	public Repositorio(){}

	public Repositorio(Dao<T> dao){
		this.dao = dao;
	}

	public void agregar(T elemento){
		this.dao.agregar(elemento);
	}

	public void eliminar(T elemento){
		this.dao.eliminar(elemento);
	}

	public void modificar(T elemento){
		this.dao.modificar(elemento);
	}

	public List<T> buscarTodos(){
		return this.dao.buscarTodos();
	}

	public T buscar(Long id) {
		return this.dao.buscar(id);
	}

	public CriteriaBuilder criteriaBuilder(){
		return EntityManagerHelper.getEntityManager().getCriteriaBuilder();
	}

	public void setDao(Dao<T> nuevoDao){
		this.dao = nuevoDao;
	}
}