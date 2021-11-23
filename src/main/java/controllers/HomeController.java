package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController {

	public ModelAndView getHome(Request request, Response response){
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("sesionIniciada", request.session().attribute("user_id") != null);

		return new ModelAndView(modelo,"main.html.hbs");
	}

	public ModelAndView getHomeAdmin(Request request, Response response){
		Map<String, Object> modelo = new HashMap<>();
		modelo.put("sesionIniciada", request.session().attribute("user_id") != null);

		return new ModelAndView(modelo,"homeAdmin.html.hbs");
	}
}
/*
	public ModelAndView show(){
		Map<String, Object> modelo = new HashMap<>();
		//modelo.put("nombreEnLaPagina", valor);
		return new ModelAndView(modelo,"archivo.html.hbs");
	}
*/