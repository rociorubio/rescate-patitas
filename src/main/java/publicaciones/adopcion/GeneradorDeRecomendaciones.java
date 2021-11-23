package publicaciones.adopcion;

import mascota.Asociacion;

import java.util.ArrayList;
import java.util.List;

public class GeneradorDeRecomendaciones {

    public List<Recomendacion> generarRecomendaciones(Asociacion asociacion) {
        List<Recomendacion> recomendaciones = new ArrayList<>();
        List<PublicacionParaAdoptar> publicacionesAdoptar = asociacion.getPublicacionesParaAdoptar(); //Es mas legible
        List<PublicacionParaDarEnAdopcion> publicacionesAdopcion = asociacion.getPublicacionesParaDarEnAdopcion(); //Es mas legible

        publicacionesAdoptar.forEach(publicacionParaAdoptar -> { //para cada publicacion para adoptar
          List<PublicacionParaDarEnAdopcion> publicacionesFiltradas = new ArrayList<>();

          publicacionesAdopcion.forEach(publicacionParaDarEnAdopcion -> { // fijate en cada publicacion para dar en adopcion

            //y compara respuestas, si son iguales (con iguales preguntas), es una recomendacion
            if(publicacionParaAdoptar.tieneMismaRespuestas(publicacionParaDarEnAdopcion.getRespuestas())){
              publicacionesFiltradas.add(publicacionParaDarEnAdopcion);
            }
          });

          if(!publicacionesFiltradas.isEmpty()){
            Recomendacion recomendacion = new Recomendacion(publicacionParaAdoptar.getPersona(), publicacionesFiltradas);
            recomendaciones.add(recomendacion);
          }

        });

        return recomendaciones;
    }

}
