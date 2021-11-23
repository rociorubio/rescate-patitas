package repositorios;

import apiHogares.Hogar;
import apiHogares.ImportadorAPIHogares;
import mascota.MascotaPerdida;
import com.sun.jersey.api.client.Client;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioHogares{
  private double radio;
  private ImportadorAPIHogares importadorAPIHogares;
  private List<Hogar> hogares;


  public RepositorioHogares(ImportadorAPIHogares importadorAPIHogares, double radio) {
    this.importadorAPIHogares = importadorAPIHogares;
    Client client = new Client();
    List<Hogar> hogares =  this.importadorAPIHogares.importarRefugios(client);
    this.hogares = hogares;
    this.radio = radio;
  }

  public List<Hogar> getHogaresAdecuados(MascotaPerdida mascota){
    if(radio < 0){
      throw new IllegalArgumentException("El radio no puede ser negativo");
    }
    return hogares.stream().filter(hogar -> hogar.esAdecuado(mascota, radio))
        .collect(Collectors.toList());
  }

  public void agregarHogar(Hogar hogar){
    hogares.add(hogar);
  }
  public List<Hogar> getHogares() {
    return hogares;
  }
  public void setRadio(double radio){this.radio = radio;}

}