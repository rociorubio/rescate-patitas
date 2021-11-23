package main;

import controllers.*;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.debug.DebugScreen;



public class Routes {
	public static void main(String[] args) {
		//new Bootstrap().run();
		Spark.port(8080);
		Spark.staticFiles.location("/public");
		DebugScreen.enableDebugScreen();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		//Controllers
		HomeController homeController = new HomeController();
		CaracteristicaController caracteristicaController = new CaracteristicaController();
		OpcionController opcionController = new OpcionController();
		SessionController sessionController = new SessionController();
		MascotaController mascotaController = new MascotaController();


		Spark.get("/caracteristicas/nueva", (req, res) -> caracteristicaController.showCrearCaracteristica(req, res), engine);
		Spark.get("/caracteristicas/:idCaracteristica/opcion/:idOpcion/eliminar", (req, res) -> opcionController.eliminarOpcionModel(req), engine);
		Spark.get("/caracteristicas/:idCaracteristica/opcion/nueva", (req, res) -> opcionController.agregarOpcionModel(req), engine);
		Spark.get("/caracteristicas", (req, res) -> caracteristicaController.showCaracteristica(req), engine);
		Spark.get("/caracteristicas/:idCaracteristica", (req, res) -> opcionController.showOpciones(req), engine);
		Spark.get("/caracteristicas/:idCaracteristica/opcion/:idOpcion", (req, res) -> opcionController.detalleOpcion(req), engine);
		Spark.get("/caracteristicas/:idCaracteristica/opcion/:idOpcion/eliminar/si", opcionController::eliminarOpcion);
		Spark.get("/caracteristicas/:idCaracteristica/eliminar/si", caracteristicaController::eliminarCaracteristicaModel);
		Spark.get("/caracteristicas/:idCaracteristica/eliminar", (request, response) -> caracteristicaController.eliminarCaracteristicaModel(request), engine);

		Spark.post("/caracteristicas", caracteristicaController::crearCaracteristica);
		Spark.post("/caracteristicas/:idCaracteristica/opcion/", opcionController::crearOpcion);
		Spark.post("/caracteristicas/:idCaracteristica/opcion/:idOpcion", opcionController::editarOpcion);


		Spark.get("/",  (request, response) -> { response.redirect("/home"); return null; });
		Spark.get("/home", (req, res) -> homeController.getHome(req, res), engine);
		Spark.get("/login", (req,res) -> sessionController.mostrarLogin(req, res), engine);
		Spark.get("/login/error", (req,res) -> new ModelAndView(null, "sessionError.html.hbs"), engine);
		Spark.post("/login", sessionController::iniciarSesion);
		Spark.get("/logout", sessionController::cerrarSesion);
		Spark.get("/usuario/nuevo", (request, response) -> new ModelAndView(null, "newSession.html.hbs"), engine);
		Spark.post("/usuario/nuevo", (request, response) -> sessionController.crearSesion(request, response), engine);


		Spark.get("/mascotas", (request, response) -> mascotaController.showMascotas(request, response), engine);
		Spark.get("/mascotas/nueva", (request, response) -> mascotaController.nuevaMascota(request, response), engine);
		Spark.post("/mascotas/nueva", mascotaController::ingresarNuevaMascota);
		Spark.get("/mascotas/encontrada", (request, response) -> mascotaController.mascotaEncontrada(request, response), engine);
		Spark.post("/mascotas/encontrada", (request, response) -> mascotaController.ingresarMascotaEncontrada(request, response), engine);

		Spark.get("/mascotas/perdidas", (request, response) -> mascotaController.getMascotasPerdidas(request, response), engine);
		Spark.get("/mascotas/adopcion", (request, response) -> new ModelAndView(null, "mascotasEnAdopcion.html.hbs"), engine);
		Spark.get("/homeAdmin", (req, res) -> homeController.getHomeAdmin(req, res), engine);


		//Spark.get("/home", (request, response) -> new ModelAndView(request.queryParamOrDefault("nombre", "Mundo"),
		//																						"main.html.hbs"), engine);
	}

}
