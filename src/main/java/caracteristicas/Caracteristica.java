package caracteristicas;

import exceptions.CaracteristicaInvalidaException;
import publicaciones.adopcion.Opcion;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Caracteristica {

    @Id
    @GeneratedValue
    private Long idCaracteristica;

    private String nombre;

    public Caracteristica(String nombre) {
        if(nombre == null) throw new CaracteristicaInvalidaException("Caracteristica libre invalida");
        this.nombre = nombre;
    }

    public Caracteristica() {

    }

    public String getNombre(){ return nombre; }

    public Long getIdCaracteristica() {
        return idCaracteristica;
    }

    public boolean validarOpcion(String opcion){ return true; }

    public abstract List<Opcion> getOpciones();
    public abstract Caracteristica modificarOpcion(Opcion opcionAntigua, Opcion opcionNueva);
    public abstract void agregarOpcion(Opcion opcion);
}
