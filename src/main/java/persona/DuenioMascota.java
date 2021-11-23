package persona;

import exceptions.DuenioMascotaException;
import persona.usuario.Usuario;

import javax.persistence.*;
import java.util.List;

@Entity
//@DiscriminatorValue("DM")
public class DuenioMascota{

    @Id
    @GeneratedValue
    public Long idDuenioMascota;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Persona persona;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario;

    public DuenioMascota(Persona persona, Usuario usuario) {
        this.persona = persona;
        if(usuario == null) throw new DuenioMascotaException("DuenioMascota invalido");
        this.usuario = usuario;
    }

    public DuenioMascota() {

    }

    public void notificar(String texto){
        persona.notificar(texto);
    }

    public void agregarContacto(MetodoDeContacto contacto){
        persona.agregarContacto(contacto);
    }

    public List<MetodoDeContacto> getContactos(){
        return persona.getContactos();
    }

    public Usuario getUsuario() { return usuario; }

    public String getEmail() { return persona.getMail(); }

    public Long getIdDuenioMascota() {
        return idDuenioMascota;
    }

}
