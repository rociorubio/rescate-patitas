package repositorios.dao;

import java.util.List;

public interface Dao<T> {

	void agregar(T elemento);

	void eliminar(T elemento);

	void modificar(T elemento);

	List<T> buscarTodos();

	T buscar(Long id);

/*
	T buscar(BusquedaCondicional condicional);
 */

}
