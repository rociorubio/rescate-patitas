package persona;

import exceptions.ContactoInvalidoException;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length=1)
public class MetodoDeContacto {

  @Id
  @GeneratedValue
  public Long idMetodoDeContacto;

  protected String nombreApellido;
  protected Integer telefono;
  protected String email;

  public MetodoDeContacto(String nombreApellido, Integer telefono, String email){
    if(nombreApellido == null || telefono == null ) throw new ContactoInvalidoException("Contacto invalido");
    this.nombreApellido = nombreApellido;
    this.telefono = telefono;
    this.email = email;
  }

  public MetodoDeContacto() {

  }

  public void notificar(String mensaje){}
  public String getNombreApellido(){ return nombreApellido; }
  public Integer getTelefono(){ return telefono; }
  public String getEmail(){ return email; }
  public String toString(){ return "Telefono: " + telefono; }
}

