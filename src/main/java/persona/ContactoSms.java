package persona;

import exceptions.EnvioRechazadoException;
import mensajeria.TwilioSMS;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("S")
public class ContactoSms extends MetodoDeContacto {
  @Transient
  private TwilioSMS enviadorSms = new TwilioSMS();

  public ContactoSms(String nombreApellido, Integer telefono, String email) {
    super(nombreApellido, telefono, email);
  }

  public ContactoSms() {

  }
  @Override
  public void notificar(String mensaje){
    try {
      enviadorSms.enviar();
    } catch (Exception e) {
      throw new EnvioRechazadoException("No se ha podido completar el envio de email");
    }
  }
}
