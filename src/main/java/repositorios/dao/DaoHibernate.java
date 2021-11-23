package repositorios.dao;
import db.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DaoHibernate<T> implements Dao<T>{

	private Class<T> type;

	public DaoHibernate(Class<T> type) {
		this.type = type;
	}

	@Override
	public void agregar(Object elemento) {
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		EntityManagerHelper.getEntityManager().persist(elemento);
		EntityManagerHelper.getEntityManager().getTransaction().commit();
	}

	@Override
	public void eliminar(Object elemento) {
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		EntityManagerHelper.getEntityManager().remove(elemento);
		EntityManagerHelper.getEntityManager().getTransaction().commit();
	}

	@Override
	public void modificar(Object elemento) {
		EntityManagerHelper.getEntityManager().getTransaction().begin();
		EntityManagerHelper.getEntityManager().merge(elemento);
		EntityManagerHelper.getEntityManager().getTransaction().commit();
	}

	@Override
	public List<T> buscarTodos() {
		CriteriaBuilder builder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(this.type);

		criteria.from(this.type);
		return EntityManagerHelper.getEntityManager()
				.createQuery(criteria)
				.getResultList();
	}

	@Override
	public T buscar(Long id) {
		return EntityManagerHelper.getEntityManager().find(type, id);
	}


/*
	@Override
	public T buscar(BusquedaCondicional condicional) {
		return (T) EntityManagerHelper.getEntityManager()
				.createQuery(condicional
						.getCondicionCriterio())
				.getSingleResult();
	}
 */

}
