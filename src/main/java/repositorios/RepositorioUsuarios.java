package repositorios;

import caracteristicas.Caracteristica;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import persona.DuenioMascota;
import persona.usuario.Hasheador;
import persona.usuario.Usuario;
import repositorios.dao.Dao;

import java.util.List;
import java.util.NoSuchElementException;

public class RepositorioUsuarios extends Repositorio implements WithGlobalEntityManager {

    public RepositorioUsuarios(Dao<Caracteristica> dao) {
        super(dao);
    }

    public List<Usuario> listar() {
        return entityManager()//
                .createQuery("from Usuario", Usuario.class) //
                .getResultList();
    }

    public Usuario getById(Long id){
        return entityManager().find(Usuario.class, id);
    }

    public Usuario buscarPorUsuario(String username){
        return listar().stream()
                .filter(u -> u.getUsername().equals(username)).findFirst().get();
    }


    public Usuario buscarPorUsuarioYContrasenia(String username, String password) {
        System.out.println("paso por acá");
        Hasheador hash = new Hasheador();
        Usuario usuario = buscarPorUsuario(username);

        if (usuario == null || hash.deshashear(password, usuario.getPassword()) == false ) {
            throw new NoSuchElementException();
        }

        return usuario;
    }

//u.getPassword().equals(password) &&
}
