package mascota;

import javax.persistence.*;

@Embeddable
public class Ubicacion {
  private double latitud;
  private double longitud;

  public Ubicacion(double latitud, double longitud){
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public Ubicacion() {
  }

  public double distance(Ubicacion unaUbicacion) {
    final int R = 6371; // Radius of the earth
    double latDistance = Math.toRadians(latitud - unaUbicacion.getLatitud());
    double lonDistance = Math.toRadians(longitud - unaUbicacion.getLongitud());
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(unaUbicacion.getLatitud())) * Math.cos(Math.toRadians(latitud))
        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c * 1000; // convert to meters

    distance = Math.pow(distance, 2);

    return Math.sqrt(distance);
  }

  public Double getLatitud() { return latitud; }
  public Double getLongitud() { return longitud; }
/*
  public boolean coincideUbicacion(Double latitud, Double longitud){
    return (getLatitud().equals(latitud) && getLongitud().equals(longitud));
  }
*/
}
