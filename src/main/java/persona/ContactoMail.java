package persona;

import exceptions.EnvioRechazadoException;
import mensajeria.JavaMail;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("M")
public class ContactoMail extends MetodoDeContacto {
  @Transient
  private JavaMail javaMail = new JavaMail();

  public ContactoMail(String nombreApellido, Integer telefono, String email) {
    super(nombreApellido, telefono, email);
  }

  public ContactoMail() {
  }

  @Override
  public void notificar(String mensaje){
    try {
      javaMail.enviar();
    } catch (Exception e) {
      throw new EnvioRechazadoException("No se ha podido completar el envio de email");
    }
  }

}
