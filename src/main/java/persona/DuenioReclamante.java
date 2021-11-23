package persona;

import exceptions.NotificacionRescatistaDuenioReclamanteException;
import mascota.PublicacionMascotaPerdida;

import javax.persistence.*;

@Entity
//@DiscriminatorValue("DR")
public class DuenioReclamante {

  @Id
  @GeneratedValue
  public Long idDuenioReclamante;

  @OneToOne (cascade = CascadeType.PERSIST)
  private Persona persona;

  @OneToOne(cascade = CascadeType.ALL)
  private Direccion direccion;

  @OneToOne(cascade = CascadeType.ALL)
  private PublicacionMascotaPerdida publicacion;

  public DuenioReclamante(Persona persona, Direccion direccion, PublicacionMascotaPerdida publicacion) {
    this.persona = persona;
    this.direccion = direccion;
    this.publicacion = publicacion;
  }

  public DuenioReclamante() {

  }

  public void notificacionRescatistaDuenioReclamaMascota() {
    try {
      persona.notificar("Su mascota ha sido encontrada! " + persona.getDescripcionDeContacto());
      publicacion.resuelto();

    } catch (Exception e) {
      throw new NotificacionRescatistaDuenioReclamanteException("No fue posible procesar su formulario, intentelo de nuevo.");
    }}


  public Direccion getDireccion() { return direccion; }
}
