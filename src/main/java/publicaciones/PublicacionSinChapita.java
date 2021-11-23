package publicaciones;

import mascota.MascotaPerdida;
import persona.Rescatista;

import javax.persistence.*;

@Entity
public class PublicacionSinChapita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacionSinChapita;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idRescatista")
    private Rescatista rescatista;

    @Enumerated(EnumType.STRING)
    private EstadoDePublicacion estadoDePublicacion;

    @OneToOne(cascade = CascadeType.PERSIST)
    private MascotaPerdida mascotaPerdida;

    public PublicacionSinChapita(Rescatista rescatista, MascotaPerdida mascotaPerdida) {
        this.rescatista = rescatista;
        this.mascotaPerdida = mascotaPerdida;
        this.estadoDePublicacion = EstadoDePublicacion.PENDIENTE_REVISION;
    }

    public PublicacionSinChapita() {

    }

    public boolean esPublicacionAprobada(){
        return estadoDePublicacion == EstadoDePublicacion.APROBADO;
    }

    public EstadoDePublicacion getEstadoDePublicacion() {
        return estadoDePublicacion;
    }
    public void aprobar() {
      this.estadoDePublicacion = EstadoDePublicacion.APROBADO;
    }
    public void rechazar() {
      this.estadoDePublicacion = EstadoDePublicacion.NO_APROBADO;
    }
    public MascotaPerdida getMascotaPerdida() {
        return mascotaPerdida;
    }

    public Rescatista getRescatista() { return rescatista; }
}
