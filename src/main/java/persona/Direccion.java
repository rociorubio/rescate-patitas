package persona;

import exceptions.DireccionException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Direccion {

  @Id
  @GeneratedValue
  public Long idDireccion;

  private String calle;
  private String altura;
  private String piso;
  private String localidad;
  private String provincia;

  public Direccion(String calle, String altura, String piso, String localidad, String provincia){
    if(calle == null || altura == null || localidad == null || provincia == null) {
      throw new DireccionException("Dirección invalida, complete todos los campos solicitados.");
    }
    this.calle = calle;
    this.altura = altura;
    this.piso = piso;
    this.localidad = localidad;
    this.provincia = provincia;
  }

  public Direccion() {

  }

  public String getCalle() {
    return calle;
  }
  public String getAltura() {
    return altura;
  }
  public String getPiso() {
    return piso;
  }
  public String getLocalidad() {
    return localidad;
  }
  public String getProvincia() {
    return provincia;
  }

}
