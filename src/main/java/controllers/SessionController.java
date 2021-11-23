package controllers;

import caracteristicas.Caracteristica;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import persona.usuario.TipoUsuario;
import persona.usuario.Usuario;
import repositorios.RepositorioCaracteristicas;
import repositorios.RepositorioUsuarios;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import validadores.*;

import javax.jws.WebParam;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class SessionController implements WithGlobalEntityManager, TransactionalOps {

    Dao dao = new DaoHibernate<>(Usuario.class);
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios(dao);


    public ModelAndView mostrarLogin(Request request, Response response) {
        if (request.session().attribute("user_id") != null) {
            response.redirect("/home");
            return null;
        }
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("sesionIniciada", request.session().attribute("user_id") != null);
        return new ModelAndView(modelo, "session.html.hbs");
    }

    public Void iniciarSesion(Request request, Response response) {
        try {
            System.out.println(request.queryParams("username"));
            System.out.println(request.queryParams("password"));

            Usuario usuario = repositorioUsuarios.buscarPorUsuarioYContrasenia(
                    request.queryParams("username"),
                    request.queryParams("password"));


            request.session().attribute("user_id", usuario.getIdUsuario());

            if (usuario.getTipoUsuario() == TipoUsuario.USUARIO_ADMINISTRADOR){
                response.redirect("/homeAdmin");
            }
            else {
                response.redirect("/home");
            }
            return null;
        } catch (NoSuchElementException e) {
            System.out.println(request.queryParams("username"));
            System.out.println(request.queryParams("password"));
            response.redirect("/login/error");
            return null;
        }
    }

    public ModelAndView crearSesion(Request request, Response response) {
        try {
            Validador validadorLongitud = new ValidadorDeLongitud(8,64);
            Validador validadorContenido = new ValidadorContenido();
            Validador validadorEnBlackList = new ValidadorEnBlackList();
            ValidadorContrasenia validadorContrasenia = new ValidadorContrasenia(validadorLongitud, validadorContenido, validadorEnBlackList);
            Usuario usuario = new Usuario(request.queryParams("username"), request.queryParams("password"), TipoUsuario.USUARIO_NORMAL, validadorContrasenia);

            System.out.println(request.queryParams("username"));
            System.out.println(request.queryParams("password"));

            request.session().attribute("user_id", usuario.getIdUsuario());

            repositorioUsuarios.agregar(usuario);

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("sesionIniciada", request.session().attribute("user_id") != null);

            return new ModelAndView(modelo, "main.html.hbs");

        } catch (NoSuchElementException e) {

            return new ModelAndView(null, "session.html.hbs");
        }
    }

    public Void cerrarSesion(Request request, Response response) {
        request.session().removeAttribute("user_id");
        System.out.println(request.session().attribute("user_id") != null);
        response.redirect("/home");
        return null;
    }
}