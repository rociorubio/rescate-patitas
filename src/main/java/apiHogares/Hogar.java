package apiHogares;

import mascota.TamanioMascota;
import mascota.MascotaPerdida;
import mascota.Ubicacion;

import java.util.List;

public class Hogar {
  String id;
  String nombre;
  Ubicacion ubicacion;
  String telefono;
  Admision admisiones;
  Integer capacidad;
  Integer lugaresDisponibles;
  Boolean patio;
  List<String> caracteristicas;

  public Hogar(String id, String nombre, String telefono, Integer capacidad, Integer lugaresDisponibles, Boolean patio) {
    this.id = id;
    this.nombre = nombre;
    this.telefono = telefono;
    this.capacidad = capacidad;
    this.lugaresDisponibles = lugaresDisponibles;
    this.patio = patio;
  }


  public boolean esAdecuado(MascotaPerdida mascota, double radio){
    Ubicacion ubicacion2 = new Ubicacion(ubicacion.getLatitud(),ubicacion.getLongitud());
    return tieneCapacidad() && tieneCaracteristicasAdecuadas(mascota)
        && esTipoDeMascotaAceptado(mascota)
        && tamanioAdecuado(mascota)
        && ubicacion2.distance(mascota.getUbicacionMascota()) < radio;
  }

  public boolean tieneCapacidad(){
    return capacidad > lugaresDisponibles;
  }

  public boolean tieneCaracteristicasAdecuadas(MascotaPerdida mascota){
    return caracteristicas.contains(mascota.getDescripcionFisica());
  }

  public boolean esTipoDeMascotaAceptado(MascotaPerdida mascota){
    return (mascota.esPerro() && admisiones.isPerros())
        || (mascota.esGato()  && admisiones.isGatos());
  }

  public boolean tamanioAdecuado(MascotaPerdida mascota){
    return (patio && (mascota.getTamanioMascota() == TamanioMascota.GRANDE || mascota.getTamanioMascota() == TamanioMascota.MEDIANO))
         ||(!patio && mascota.getTamanioMascota() == TamanioMascota.CHICO);
  }

  public String getId() { return id; }
  public String getNombre() { return nombre; }
  public Ubicacion getUbicacion() { return ubicacion; }
  public String getTelefono() { return telefono; }
  public Admision getAdmisiones() { return admisiones; }
  public int getCapacidad() { return capacidad; }
  public int getLugaresDisponibles() { return lugaresDisponibles; }
  public Boolean getPatio() { return patio; }
  public List<String> getCaracteristicas() { return caracteristicas; }
  public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }
  public void setAdmisiones(Admision admisiones) { this.admisiones = admisiones; }
  public void setCaracteristicas(List<String> caracteristicas) { this.caracteristicas = caracteristicas; }
}
