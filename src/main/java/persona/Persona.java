package persona;

import exceptions.PersonaInvalidaException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Entity
public class Persona {

    @Id
    @GeneratedValue
    public Long idPersona;

    private String nombre;

    private String apellido;

    private Integer telefono;

    private String mail;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    private Integer documento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMetodoDeContacto")
    private List<MetodoDeContacto> contactos = new ArrayList<>();

    public Persona(String nombre, String apellido, Integer telefono, String mail, TipoDocumento tipoDocumento, Integer documento) {
        if(nombre == null || apellido == null || telefono == null || mail == null || tipoDocumento == null ||  documento == null)
            throw new PersonaInvalidaException("Persona invalida");

        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
    }

    public Persona() {

    }

    public void notificar(String mensaje){contactos.forEach(contacto -> contacto.notificar(mensaje));}

    public void agregarContacto(MetodoDeContacto contacto){
        contactos.add(contacto);
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public Integer getTelefono() {
        return telefono;
    }
    public String getMail() {
        return mail;
    }
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }
    public Integer getDocumento() {
        return documento;
    }
    public List<MetodoDeContacto> getContactos() {
        return contactos;
    }

    public String getDescripcionDeContacto() {
        AtomicReference<String> descripcion = new AtomicReference<>("");

        getContactos().stream()
                .forEach(contacto -> descripcion.set(descripcion + "\n" + contacto.toString()));

        return descripcion.get();
    }

    public Long getId() {
        return idPersona;
    }
}
