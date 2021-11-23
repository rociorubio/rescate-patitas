package mascota;
import persona.Rescatista;
import publicaciones.EstadoDePublicacion;

import javax.persistence.*;

@Entity
public class PublicacionMascotaPerdida {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(cascade = CascadeType.PERSIST)
  private Rescatista rescatista;

  @OneToOne(cascade = CascadeType.PERSIST)
  private MascotaPerdida mascotaPerdida;

  @Enumerated(EnumType.STRING)
  private EstadoDePublicacion estadoDePublicacion;

  public PublicacionMascotaPerdida(Rescatista rescatista, MascotaPerdida mascotaPerdida){
    this.rescatista = rescatista;
    this.mascotaPerdida = mascotaPerdida;
    this.estadoDePublicacion = EstadoDePublicacion.PENDIENTE_REVISION;
  }

  public PublicacionMascotaPerdida() {

  }

  public EstadoDePublicacion getEstadoDePublicacion(){ return estadoDePublicacion; }
  public String getMailRescatista(){
    return rescatista.getEmail();
  }
  public MascotaPerdida getMascotaPerdida(){ return mascotaPerdida; }
  public void aprobar(){this.estadoDePublicacion = EstadoDePublicacion.APROBADO;}
  public void rechazar(){this.estadoDePublicacion = EstadoDePublicacion.NO_APROBADO;}
  public void pendiente() {this.estadoDePublicacion = EstadoDePublicacion.PENDIENTE_REVISION;}
  public void resuelto() {this.estadoDePublicacion = EstadoDePublicacion.RESUELTO;}

}
