package repositorios;

import publicaciones.adopcion.PublicacionParaDarEnAdopcion;
import repositorios.dao.Dao;


public class RepositorioPublicacionParaDarEnAdopcion extends Repositorio{

  public RepositorioPublicacionParaDarEnAdopcion(Dao<PublicacionParaDarEnAdopcion> dao) {
    super(dao);
  }


}
