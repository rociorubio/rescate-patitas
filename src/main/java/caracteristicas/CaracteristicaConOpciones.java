package caracteristicas;

import exceptions.CaracteristicaInvalidaException;
import publicaciones.adopcion.Opcion;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
public class CaracteristicaConOpciones extends Caracteristica {

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Opcion> opciones;

    public CaracteristicaConOpciones(String nombre, List<Opcion> opciones) {
        super(nombre);
        if(opciones == null) throw new CaracteristicaInvalidaException("Caracteristica con opciones invalida");
        this.opciones = opciones;
    }

    public CaracteristicaConOpciones() {

    }

    public Caracteristica modificarOpcion(Opcion opcionAntigua, Opcion opcionNueva){
        Opcion opcion = opciones.stream().filter(opcion1 ->
            opcion1.getOpcion().equals(opcionAntigua.getOpcion())
        ).collect(Collectors.toList()).get(0);
        opciones.remove(opcion);
        opciones.add(opcionNueva);
        return this;
    }

    @Override
    public boolean validarOpcion(String opcion){
        return opciones.stream().anyMatch(o -> o.getOpcion().equals(opcion));
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }
    public void agregarOpcion(Opcion o){
        opciones.add(o);
    }
}
