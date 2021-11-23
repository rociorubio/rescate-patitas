package controllers;

import caracteristicas.Atributo;
import caracteristicas.Caracteristica;
import caracteristicas.CaracteristicaConOpciones;
import caracteristicas.CaracteristicaLibre;
import mascota.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import persona.*;
import persona.usuario.TipoUsuario;
import persona.usuario.Usuario;
import publicaciones.PublicacionConChapita;
import publicaciones.adopcion.Opcion;
import repositorios.RepositorioCaracteristicas;
import repositorios.RepositorioMascotas;
import repositorios.RepositorioUsuarios;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import validadores.Validador;
import validadores.ValidadorContenido;
import validadores.ValidadorContrasenia;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;

public class MascotaController implements WithGlobalEntityManager, TransactionalOps {
	Dao dao = new DaoHibernate<>(Mascota.class);
	RepositorioMascotas repositorioMascotas = new RepositorioMascotas(dao);

	public ModelAndView showMascotas(Request req, Response res) {
		if (req.session().attribute("user_id") == null) {
			res.redirect("/login");
			return null;
		}

		String filtro = req.queryParams("nombre");

		List<Mascota> mascotas = filtro == null?
				repositorioMascotas.getMascotasIngresadas() :
				repositorioMascotas.buscarPorNombre(filtro);

		//List<Mascota> mascotasPorDuenio = repositorioMascotas.buscarPorDuenio(req.session().attribute("user_id"));

		Map<String, Object> modelo = new HashMap<>();
		modelo.put("mascotas", mascotas);
		modelo.put("sesionIniciada", req.session().attribute("user_id") != null);

		return new ModelAndView(modelo,"mascotas.html.hbs");

	}

	public ModelAndView nuevaMascota(Request request, Response response){
		if (request.session().attribute("user_id") == null) {
			response.redirect("/login");
			return null;
		}

		Dao dao = new DaoHibernate(Caracteristica.class);
		RepositorioCaracteristicas repositorioCaracteristicas = new RepositorioCaracteristicas(dao);

		List<Caracteristica> caracteristicas = repositorioCaracteristicas.buscarTodos();

		Map<String, Object> modelo = new HashMap<>();
		modelo.put("caracteristicas", caracteristicas);
		modelo.put("sesionIniciada", request.session().attribute("user_id") != null);

		return new ModelAndView(modelo,"nuevaMascota.html.hbs");
	}

	public ModelAndView mascotaEncontrada(Request request, Response response){
		if (request.session().attribute("user_id") == null) {
			response.redirect("/login");
			return null;
		}

		System.out.println(request.queryParams());
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("sesionIniciada", request.session().attribute("user_id") != null);

		return new ModelAndView(modelo,"mascotaEncontrada.html.hbs");
	}


	public Object ingresarNuevaMascota(Request req, Response res) {
		TipoDocumento tipoDocumento = null;
		if(req.queryParams("tipoDoc").equals("DNI")) {tipoDocumento = TipoDocumento.DNI;}

		Persona p = new Persona(
		req.queryParams("nombreDuenio"),
		req.queryParams("apellidoDuenio"),
		123456,
		"mail",
		tipoDocumento,
		Integer.parseInt(req.queryParams("nrodoc")));

		//Long idUser = req.session().attribute("user_id");

		//Usuario usuarioDuenio = repositorioUsuario.getById(idUser);



		ValidadorContenido validadorContenido = new ValidadorContenido();
		ValidadorContrasenia validadorContrasenia = new ValidadorContrasenia();
		validadorContrasenia.agregar(validadorContenido);
		Usuario usuario = new Usuario("UsuarioPrueba", "contraseniaPrueba1234rrD", TipoUsuario.USUARIO_NORMAL, validadorContrasenia);
		DuenioMascota duenioMascota = new DuenioMascota(p, usuario);

		MetodoDeContacto contacto = new MetodoDeContacto(
				req.queryParams("nombreApellidoContacto"),
				Integer.parseInt(req.queryParams("telefono")),
				req.queryParams("mail")
				);

		byte[] array = new byte[7];
		Mascota mascota;
		TipoMascota tipoMascota; SexoMascota sexoMascota;
		if(req.queryParams("tipoDeMascota").equals("Perro")) { tipoMascota = TipoMascota.PERRO; } else { tipoMascota = TipoMascota.GATO;}
		if(req.queryParams("sexoMascota").equals("Macho")){ sexoMascota = SexoMascota.MACHO;} else {sexoMascota = SexoMascota.HEMBRA; }
		mascota = new Mascota(
				req.queryParams("nombreMascota"),
				req.queryParams("apodoMascota"),
				Integer.parseInt(req.queryParams("edadMascota")),
				new ArrayList<>(), // Atributos
				tipoMascota,
				sexoMascota,
				new String(),// Foto
				new String(array, Charset.forName("UTF-8")),
				duenioMascota
		);



		try{
			withTransaction(() -> {
						repositorioMascotas.agregar(mascota);
			});

			res.redirect("/");
			return null;

		} catch (RuntimeException e){
			res.status(400);
			return e.getMessage();
		}

	}

	public ModelAndView ingresarMascotaEncontrada(Request request, Response response) {
		TipoMascota tipoMascota = TipoMascota.PERRO;;
		TamanioMascota tamanioMascota = TamanioMascota.CHICO;

		System.out.println(request.queryParams("tipoDeMascota"));
		if(request.queryParams("tipoDeMascota").equals("Gato")) {
			tipoMascota = TipoMascota.GATO;
		}

		if(request.queryParams("tamanioMascota").equals("Mediano")) {
			tamanioMascota = TamanioMascota.MEDIANO;
		}
		else if (request.queryParams("tamanioMascota").equals("Grande")){
			tamanioMascota = TamanioMascota.GRANDE;
		}

		Ubicacion ubicacion = new Ubicacion(20., 30.);
		Persona personaRescatista = new Persona(request.queryParams("nombreRescatista"),request.queryParams("apellidoContacto"), 1385464783, request.queryParams("emailContacto"), TipoDocumento.DNI, Integer.parseInt(request.queryParams("numDoc")));
		Direccion direccion = new Direccion( request.queryParams("calle"), request.queryParams("altura"), request.queryParams("pisodpto"),  request.queryParams("localidad"), request.queryParams("provincia"));
		Rescatista rescatista = new Rescatista(personaRescatista, direccion);
		String chapita = request.queryParams("numChapita");

		MascotaPerdida mascotaPerdida = new MascotaPerdida(LocalDate.now(), chapita, new ArrayList<>(), request.queryParams("descripcion"), ubicacion, tipoMascota, tamanioMascota);
		repositorioMascotas.agregar(mascotaPerdida);
		//repositorioMascotas.agregar(rescatista);

		System.out.println(request.queryParams());
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("sesionIniciada", request.session().attribute("user_id") != null);
		List<MascotaPerdida> mascotasPerdidas = repositorioMascotas.getMascotasPerdidas();
		modelo.put("mascotasPerdidas", mascotasPerdidas);

		return new ModelAndView(modelo,"mascotasPerdidas.html.hbs");
	}

	public ModelAndView getMascotasPerdidas(Request request, Response response){

		if (request.session().attribute("user_id") == null) {
			response.redirect("/login");
			return null;
		}

		Map<String, Object> modelo = new HashMap<>();
		modelo.put("sesionIniciada", request.session().attribute("user_id") != null);

		List<MascotaPerdida> mascotasPerdidas = repositorioMascotas.getMascotasPerdidas();
		modelo.put("mascotasPerdidas", mascotasPerdidas);

		return new ModelAndView(modelo,"mascotasPerdidas.html.hbs");
	}


}
/*
	public ModelAndView show(){
		Map<String, Object> modelo = new HashMap<>();
		//modelo.put("nombreEnLaPagina", valor);
		return new ModelAndView(modelo,"archivo.html.hbs");
	}
*/
