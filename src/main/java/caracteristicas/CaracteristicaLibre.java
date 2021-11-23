package caracteristicas;

import exceptions.CaracteristicaInvalidaException;
import publicaciones.adopcion.Opcion;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class CaracteristicaLibre extends Caracteristica {

    public CaracteristicaLibre(String nombre) {
        super(nombre);
    }

    public CaracteristicaLibre() {
    }

    @Override
    public boolean validarOpcion(String opcion){return true; }

    @Override
    public List<Opcion> getOpciones() {
        return null;
    }
    public Caracteristica modificarOpcion(Opcion opcionAntigua, Opcion opcionNueva){
        return null;
    }
    public void agregarOpcion(Opcion o){}
}