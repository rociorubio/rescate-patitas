package persona;

import exceptions.RescatistaInvalidoException;

import javax.persistence.*;

@Entity
//@DiscriminatorValue("RE")
public class Rescatista{

    @Id
    @GeneratedValue
    public Long idRescatista;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Persona persona;

    @OneToOne(cascade = CascadeType.ALL)
    private Direccion direccion;

    public Rescatista(Persona persona, Direccion direccion) {
        this.persona = persona;
        if(direccion == null) throw new RescatistaInvalidoException("Rescatista invalido");
        this.direccion = direccion;
    }

    public Rescatista() {
    }

    public String getEmail() { return persona.getMail(); }
    public Direccion getDireccion() {return direccion;}

}
