package controllers;

import caracteristicas.Caracteristica;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import publicaciones.adopcion.Opcion;
import repositorios.RepositorioCaracteristicas;
import repositorios.RepositorioOpciones;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpcionController implements WithGlobalEntityManager, TransactionalOps {
	Dao dao = new DaoHibernate<>(Opcion.class);
	RepositorioOpciones repositorioOpciones = new RepositorioOpciones(dao);

	Dao dao1 = new DaoHibernate<>(Caracteristica.class);
	RepositorioCaracteristicas repositorioCaracteristica = new RepositorioCaracteristicas(dao1);

	public ModelAndView showOpciones(Request req){
		String filtro = req.queryParams("opcionBuscada");

		Caracteristica caracteristica = buscarCaracteristica(req.params("idCaracteristica"));

		List<Opcion> opciones = filtro == null?
				repositorioOpciones.filtrarPorCaracteristica(req.params("idCaracteristica")) :
				repositorioOpciones.buscarPorNombre(filtro);

		Map<String, Object> modelo = new HashMap<>();
		modelo.put("opciones", opciones);
		modelo.put("caracteristica", caracteristica);
		modelo.put("sesionIniciada", req.session().attribute("user_id") != null);

		return new ModelAndView(modelo,"opcionesDeCaracteristicas.html.hbs");
	}

	public ModelAndView detalleOpcion(Request req){
		Opcion opcionBuscada = (Opcion) repositorioOpciones.buscar(
				Long.parseLong(req.params("idCaracteristica"))
		);

		Map<String, Object> modelo = new HashMap<>();
		modelo.put("opcion", opcionBuscada);
		modelo.put("idCaracteristica", req.params("idCaracteristica"));
		modelo.put("sesionIniciada", req.session().attribute("user_id") != null);

		return new ModelAndView(modelo, "editarOpciones.html.hbs");
	}

	public ModelAndView eliminarOpcionModel(Request req){
		Map<String, Object> modelo = new HashMap<>();

		Opcion opcionBuscada = (Opcion) repositorioOpciones.buscar(
				Long.parseLong(req.params("idOpcion"))
		);
		Caracteristica caracteristica = (Caracteristica) repositorioCaracteristica
				.buscar(Long.parseLong(req.params("idCaracteristica")));

		modelo.put("opcion", opcionBuscada);
		modelo.put("caracteristica", caracteristica);
		modelo.put("sesionIniciada", req.session().attribute("user_id") != null);

		return new ModelAndView(modelo, "eliminarOpcion.html.hbs");
	}

	public Object crearOpcion(Request req, Response res){
		Caracteristica caracteristica = (Caracteristica) repositorioCaracteristica.buscar(Long.parseLong(req.params("idCaracteristica")));

		try{
			Opcion nuevaOpcion = new Opcion(req.queryParams("nombre"));
			caracteristica.agregarOpcion(nuevaOpcion);

			withTransaction(() -> {
				repositorioCaracteristica.agregar(caracteristica);
			});

			res.redirect("/caracteristicas/" + req.params("idCaracteristica"));
			return null;

		} catch (RuntimeException e){
			res.status(400);
			return e.getMessage();
		}

	}

	public Object editarOpcion(Request req, Response res){
		String idCaracteristica = req.params("idCaracteristica");
		String idOpcion = req.params("idOpcion");

		Dao dao = new DaoHibernate<>(Caracteristica.class);
		RepositorioCaracteristicas repositorioCaracteristica = new RepositorioCaracteristicas(dao);

		try{
			Opcion opcionAEditar = (Opcion) repositorioOpciones.buscar(
					Long.parseLong(idOpcion)
			);

			Opcion opcionNueva = opcionAEditar;
			opcionNueva.setOpcion(req.queryParams("nombre"));

			Caracteristica caracteristica = buscarCaracteristica(idCaracteristica);
			caracteristica.modificarOpcion(opcionAEditar, opcionNueva);


			withTransaction(() -> {
					repositorioCaracteristica.modificar(caracteristica);
			});

			res.redirect("/caracteristicas/" + idCaracteristica);
			return null;

		} catch (RuntimeException e){
			res.status(400);
			return e.getMessage();
		}
	}

	public ModelAndView agregarOpcionModel(Request req){
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("idCaracteristica", req.params("idCaracteristica"));
		return new ModelAndView(modelo, "agregarOpciones.html.hbs");
	}


	public ModelAndView nuevaOpcion(Request req) {
		return new ModelAndView(null, "agregarOpciones.html.hbs");
	}

	public Caracteristica buscarCaracteristica(String idCaracteristica){
		Dao dao = new DaoHibernate(Caracteristica.class);
		RepositorioCaracteristicas repo = new RepositorioCaracteristicas(dao);
		return (Caracteristica) repo.buscar(Long.parseLong(idCaracteristica));
	}

	public Object eliminarOpcion(Request req, Response res) {
		try{
			Opcion opcion = (Opcion) repositorioOpciones.buscar(Long.parseLong(req.params("idOpcion")));
			Caracteristica caracteristica = (Caracteristica) repositorioCaracteristica.buscar(Long.parseLong(req.params("idCaracteristica")));
			withTransaction(() -> {
				repositorioOpciones.eliminar(opcion);
				repositorioCaracteristica.modificar(caracteristica);
			});
			res.redirect("/caracteristicas");
			return null;

		} catch (RuntimeException e){
			res.status(400);
			return e.getMessage();
		}
	}
}
