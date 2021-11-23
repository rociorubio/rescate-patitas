package mascota;

import exceptions.MascotaPerdidaInvalidaException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class MascotaPerdida {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ElementCollection
  @Column(name = "fotos_mp")
  private List<String> foto;

  private LocalDate fecha;
  private String chapita;
  private String descripcionFisica;

  @Embedded
  private Ubicacion ubicacion;

  @Enumerated(EnumType.STRING)
  private TipoMascota tipoMascota;

  @Enumerated(EnumType.STRING)
  private TamanioMascota tamanioMascota;

  public MascotaPerdida(LocalDate _fecha, String chapita, List<String> foto, String descripcionFisica, Ubicacion ubicacion, TipoMascota tipoMascota, TamanioMascota tamanioMascota) {
    if (foto == null || descripcionFisica == null || ubicacion == null || tamanioMascota == null || tipoMascota == null)
      throw new MascotaPerdidaInvalidaException("Revise los datos de la mascota perdida");

    this.chapita = chapita;
    this.foto = foto;
    this.descripcionFisica = descripcionFisica;
    this.ubicacion = ubicacion;
    this.fecha = _fecha;
    this.tipoMascota = tipoMascota;
    this.tamanioMascota = tamanioMascota;
  }

  public MascotaPerdida() {

  }

  public boolean esPerro() {
    return this.tipoMascota == TipoMascota.PERRO;
  }

  public boolean esGato() {
    return this.tipoMascota == TipoMascota.GATO;
  }

  public double getLatitud() {
    return ubicacion.getLatitud();
  }

  public double getLongitud() {
    return ubicacion.getLongitud();
  }

  public void setChapita(String chapita) {
    this.chapita = chapita;
  }

  public String getChapita() {
    return chapita;
  }

  public String getDescripcionFisica() {
    return descripcionFisica;
  }

  public Ubicacion getUbicacionMascota() {
    return ubicacion;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public List<String> getFoto() {
    return foto;
  }

  public TamanioMascota getTamanioMascota() {
    return tamanioMascota;
  }

  public TipoMascota getTipoMascota() {
    return tipoMascota;
  }

  public Long getId() {
    return id;
  }
}
