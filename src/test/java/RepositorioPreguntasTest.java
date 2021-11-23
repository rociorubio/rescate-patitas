import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import publicaciones.adopcion.Opcion;
import publicaciones.adopcion.Pregunta;
import repositorios.RepositorioPreguntas;
import repositorios.dao.Dao;
import repositorios.dao.DaoHibernate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepositorioPreguntasTest {
	Dao dao = new DaoHibernate(Pregunta.class);
	RepositorioPreguntas repositorioPreguntas = new RepositorioPreguntas(dao);

	List<Opcion> opciones1 = Arrays.asList(new Opcion("Si"), new Opcion("No"));
	Pregunta pregunta1 = new Pregunta("¿El perro necesita patio?", "¿Tiene patio la casa?", opciones1);

	List<Opcion> opciones2 = Arrays.asList(new Opcion("Grande"), new Opcion("Mediano") ,new Opcion("Chico"));
	Pregunta pregunta2 = new Pregunta("¿De que tamanio es el perro?", "¿De que tamanio se prefiere el perro?", opciones2);

	@Test
	public void puedoAgregarPreguntasALaBaseDeDatos(){
		repositorioPreguntas.agregar(pregunta1);
		repositorioPreguntas.agregar(pregunta2);

		assertTrue(repositorioPreguntas.buscarTodos().contains(pregunta1));
		assertTrue(repositorioPreguntas.buscarTodos().contains(pregunta2));
	}

	/* 	TODO: Ver por que cuando se corren todos los test no funciona pero si corro solo estos dos, funciona.
			TODO: Rompe el "modificar(pregunta)"
	@Test
	public void puedoModificarUnaPregunta(){
		Pregunta pregunta = (Pregunta) repositorioPreguntas.buscar((long)2);

		pregunta.setPreguntaParaDarEnAdopcion("UnaNuevaPregunta");

		repositorioPreguntas.modificar(pregunta);

		Pregunta preguntaAssert = (Pregunta) repositorioPreguntas.buscar((long)2);

		assertEquals("UnaNuevaPregunta", preguntaAssert.getPreguntaParaDarEnAdopcion());
	}
*/
}
