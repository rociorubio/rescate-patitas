package publicaciones.adopcion;

import persona.Persona;

import java.util.List;

public class Recomendacion {
    private Persona persona;
    private List<PublicacionParaDarEnAdopcion> publicaciones;

    public Recomendacion(Persona persona, List<PublicacionParaDarEnAdopcion> publicaciones) {
        this.persona = persona;
        this.publicaciones = publicaciones;
    }

    //TODO: Hacer bien el notificar
    public void notificar() {
        StringBuilder stringBuilder = new StringBuilder();

        publicaciones.stream()
                //publicacion.getMascota().getApodo()
                .forEach(publicacion -> stringBuilder.append("Lo que yo quiera"));

        persona.notificar("Tienes recomendaciones pendientes: \n" + stringBuilder.toString());
    }

    public Persona getPersona() {
        return persona;
    }

    public List<PublicacionParaDarEnAdopcion> getPublicaciones() {
        return publicaciones;
    }
}
