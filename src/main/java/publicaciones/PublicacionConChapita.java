package publicaciones;

import mascota.Mascota;
import persona.DuenioMascota;
import persona.Rescatista;

import javax.persistence.*;

@Entity
public class PublicacionConChapita {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPublicacionConChapita;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "idRescatista")
  private Rescatista rescatista;

  @Enumerated(EnumType.STRING)
  private EstadoDePublicacion estadoDePublicacion;

  @OneToOne(cascade = CascadeType.PERSIST)
  private Mascota mascota;

  public PublicacionConChapita(Rescatista rescatista, Mascota mascota) {
    this.rescatista = rescatista;
    this.mascota = mascota;
    this.estadoDePublicacion = EstadoDePublicacion.PENDIENTE_REVISION;
  }

  public PublicacionConChapita() {

  }

  public void aprobar() {
    this.estadoDePublicacion = EstadoDePublicacion.APROBADO;
    notificarDuenioMascotaEncontrada();
  }

  public void notificarDuenioMascotaEncontrada() {
    DuenioMascota duenio = mascota.getDuenioMascota();
    duenio.notificar("Hemos encontrado a tu mascota, nos contactaremos contigo!");
  }

  public void rechazar() {
    this.estadoDePublicacion = EstadoDePublicacion.NO_APROBADO;
  }
  public boolean esPublicacionAprobada(){
    return estadoDePublicacion == EstadoDePublicacion.APROBADO;
  }
  public EstadoDePublicacion getEstadoDePublicacion() {
    return estadoDePublicacion;
  }

  public Rescatista getRescatista() { return rescatista; }
}