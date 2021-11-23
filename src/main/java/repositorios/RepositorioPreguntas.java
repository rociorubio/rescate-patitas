package repositorios;

import publicaciones.adopcion.Pregunta;
import repositorios.dao.Dao;

public class RepositorioPreguntas extends Repositorio{

    public RepositorioPreguntas(Dao<Pregunta> dao) {
        super(dao);
    }

}
