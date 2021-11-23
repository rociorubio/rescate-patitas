package controllers;

import caracteristicas.Caracteristica;
import caracteristicas.CaracteristicaConOpciones;
import caracteristicas.CaracteristicaLibre;
import mascota.Mascota;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import publicaciones.adopcion.Opcion;
import repositorios.RepositorioCaracteristicas;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaracteristicaController  implements WithGlobalEntityManager, TransactionalOps {
	Dao dao = new DaoHibernate<>(Caracteristica.class);
	RepositorioCaracteristicas repositorioCaracteristicas = new RepositorioCaracteristicas(dao);


	public ModelAndView showCaracteristica(Request req){
		String filtro = req.queryParams("caracteristicaBuscada");

		List<Caracteristica> caracteristicas = filtro == null?
				repositorioCaracteristicas.buscarTodos() :
				repositorioCaracteristicas.buscarPorNombre(filtro);

		Map<String, Object> modelo = new HashMap<>();
		modelo.put("caracteristicas", caracteristicas);
		modelo.put("sesionIniciada", req.session().attribute("user_id") != null);

		return new ModelAndView(modelo,"editarCaracteristicas.html.hbs");
	}


	public ModelAndView detalleCaracteristica(Request req){
		Caracteristica caracteristica = (Caracteristica) repositorioCaracteristicas.buscar(
				Long.parseLong(req.params("idCaracteristica"))
		);
		System.out.print(caracteristica.getNombre());

		Map<String, Object> modelo = new HashMap<>();
		modelo.put("caracteristica", caracteristica);
		modelo.put("sesionIniciada", req.session().attribute("user_id") != null);
		return new ModelAndView(caracteristica, "opcionesDeCaracteristicas.html.hbs");
	}

	public ModelAndView showCrearCaracteristica (Request req, Response res) {
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("sesionIniciada", req.session().attribute("user_id") != null);
		return new ModelAndView(modelo, "agregarCaracteristica.html.hbs");
	}


	public Object crearCaracteristica(Request req, Response res){

		try{
			Caracteristica nuevaCaracteristica = crearCarateristicaCorrecta(req);

			withTransaction(() -> {
				repositorioCaracteristicas.agregarCaracteristica(nuevaCaracteristica);
			});
			res.redirect("/caracteristicas/" + nuevaCaracteristica.getIdCaracteristica());
			return null;

		} catch (RuntimeException e){
			res.status(400);
			return e.getMessage();
		}

	}

	public Object eliminarCaracteristicaModel(Request req, Response res){

		try{
			Caracteristica caracteristica = (Caracteristica) repositorioCaracteristicas.buscar(Long.parseLong(req.params("idCaracteristica")));

			withTransaction(() -> {
				repositorioCaracteristicas.eliminar(caracteristica);
			});
			res.redirect("/caracteristicas");
			return null;

		} catch (RuntimeException e){
			res.status(400);
			return e.getMessage();
		}

	}

	public ModelAndView eliminarCaracteristicaModel(Request req) {
		Map<String, Object> modelo = new HashMap<>();
		Caracteristica caracteristica = (Caracteristica) repositorioCaracteristicas.buscar(
				Long.parseLong(req.params("idCaracteristica"))
		);
		modelo.put("caracteristica", caracteristica);
		return new ModelAndView(modelo, "eliminarCaracteristica.html.hbs");
	}

	// Auxiliares
	public Caracteristica crearCarateristicaCorrecta(Request req){
		Caracteristica nuevaCaracteristica;
		if(req.queryParams("conOpcion").equals("SI")){
			List<Opcion> opciones = new ArrayList<>();
			nuevaCaracteristica = new CaracteristicaConOpciones(req.queryParams("nombre"), opciones);
		} else {
			nuevaCaracteristica = new CaracteristicaLibre(req.queryParams("nombre"));
		}
		return nuevaCaracteristica;
	}

}
