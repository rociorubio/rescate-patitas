package apiHogares;

import mascota.Ubicacion;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import java.util.ArrayList;
import java.util.List;

public class ImportadorAPIHogares {
  HogaresRestClient hogaresRestClient;

  public ImportadorAPIHogares() {
    this.hogaresRestClient = new HogaresRestClient();
  }

  public List<Hogar> importarRefugios(Client client){
    List<Hogar> hogares = new ArrayList<>();
    boolean seguirConsultando = true;
    int pagina = 1;
    do {
      ClientResponse clientResponse = hogaresRestClient.getHogares(Integer.toString(pagina), client);

      if (clientResponse.getStatus() != 200) {
        seguirConsultando = false;
      } else {
        pagina++;

        String json = clientResponse.getEntity(String.class);
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(json).getAsJsonObject();

        JsonArray hogs = obj.get("hogares").getAsJsonArray();

        //int lengthJson = obj.getJSONArray("hogares").length();

        for (JsonElement h : hogs) {
          JsonObject gsonHogar = h.getAsJsonObject();
          Hogar hogar = generarHogar(gsonHogar);
          Admision admision = generarTipoDeAdmisiones(gsonHogar);
          Ubicacion ubicacion = generarUbicacion(gsonHogar);
          List<String> caracteristicas = generarCaracteristicas(gsonHogar);

          hogar.setAdmisiones(admision);
          hogar.setUbicacion(ubicacion);
          hogar.setCaracteristicas(caracteristicas);

          hogares.add(hogar);
        }
      }
    } while (seguirConsultando);

    return hogares;

  }

  private Hogar generarHogar(JsonObject hogar) {

    String id = hogar.get("id").getAsString();
    String nombre = hogar.get("nombre").getAsString();
    String telefono = hogar.get("telefono").getAsString();
    Integer capacidad = hogar.get("capacidad").getAsInt();
    Integer lugares_disponibles = hogar.get("lugares_disponibles").getAsInt();
    boolean patio = hogar.get("patio").getAsBoolean();

    return new Hogar(id, nombre, telefono, capacidad, lugares_disponibles, patio);

    /*
    String id = obj.getJSONArray("hogares").getJSONObject(j).getString("id");
    String nombre = obj.getJSONArray("hogares").getJSONObject(j).getString("nombre");
    String telefono = obj.getJSONArray("hogares").getJSONObject(j).getString("telefono");
    Integer capacidad = obj.getJSONArray("hogares").getJSONObject(j).getInt("capacidad");
    Integer lugares_disponibles = obj.getJSONArray("hogares").getJSONObject(j).getInt("lugares_disponibles");
    boolean patio = obj.getJSONArray("hogares").getJSONObject(j).getBoolean("patio");
    return new Hogar(id, nombre, telefono, capacidad, lugares_disponibles, patio);

     */
  }

  public Admision generarTipoDeAdmisiones(JsonObject hogar){

    JsonObject admisiones = hogar.get("admisiones").getAsJsonObject();
    boolean admisiones_perros = admisiones.get("perros").getAsBoolean();
    boolean admisiones_gatos = admisiones.get("gatos").getAsBoolean();

    return new Admision(admisiones_perros, admisiones_gatos);

    /*
    boolean admisiones_perros = obj.getJSONArray("hogares").getJSONObject(j).getJSONObject("admisiones").getBoolean("perros");
    boolean admisiones_gatos = obj.getJSONArray("hogares").getJSONObject(j).getJSONObject("admisiones").getBoolean("gatos");
    return new Admision(admisiones_perros, admisiones_gatos);
     */
  }

  public Ubicacion generarUbicacion(JsonObject hogar) {

    JsonObject ubicacion = hogar.get("ubicacion").getAsJsonObject();

    Double ubicacion_longitud = ubicacion.get("long").getAsDouble();
    Double ubicacion_lat = ubicacion.get("lat").getAsDouble();

    return new Ubicacion(ubicacion_lat, ubicacion_longitud);

    /*
    Double ubicacion_lat = obj.getJSONArray("hogares").getJSONObject(j).getJSONObject("ubicacion").getDouble("lat");
    Double ubicacion_longitud = obj.getJSONArray("hogares").getJSONObject(j).getJSONObject("ubicacion").getDouble("long");
    return new Ubicacion(ubicacion_lat, ubicacion_longitud);

     */
  }

  public List<String> generarCaracteristicas(JsonObject hogar){

    int length = hogar.get("caracteristicas").getAsJsonArray().size();
    List<String> caracteristicas = new ArrayList<>();

    for(int i = 0; i < length; i++){
      String aux = hogar.get("caracteristicas").getAsJsonArray().get(i).getAsString();
      caracteristicas.add(aux);
    }

    return caracteristicas;

    /*
    List<String> caracteristicas = new ArrayList<>();
    int length = obj.getJSONArray("hogares").getJSONObject(j).getJSONArray("caracteristicas").length();
    for (int i = 0; i < length; i++) {
      String aux = obj.getJSONArray("hogares").getJSONObject(j).getJSONArray("caracteristicas").getString(i);
      caracteristicas.add(aux);
    }
    return caracteristicas;

     */
  }


}
