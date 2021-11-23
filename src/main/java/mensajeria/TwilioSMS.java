package mensajeria;

import javax.persistence.Embeddable;

@Embeddable
public class TwilioSMS {
  public void enviar() {
    System.out.print("TwilioSMS");
  }
}

