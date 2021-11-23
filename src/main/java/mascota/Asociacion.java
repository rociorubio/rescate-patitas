package mascota;

import publicaciones.adopcion.Pregunta;
import publicaciones.adopcion.PublicacionParaAdoptar;
import publicaciones.adopcion.PublicacionParaDarEnAdopcion;
import publicaciones.PublicacionConChapita;
import publicaciones.PublicacionSinChapita;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity

public class Asociacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsociacion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPublicacionSinChapita")
    private List<PublicacionSinChapita> publicacionesSinChapita = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPublicacionConChapita")
    private List<PublicacionConChapita> publicacionesConChapita = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPublicacionParaAdoptar")
    private List<PublicacionParaAdoptar> publicacionesParaAdoptar = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPublicacionParaDarEnAdopcion")
    private List<PublicacionParaDarEnAdopcion> publicacionesParaDarEnAdopcion = new ArrayList<>();

    @Embedded
    private Ubicacion ubicacionAsociacion;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Pregunta> preguntasParaAdopcion;

    public Asociacion(Ubicacion ubicacion, List<Pregunta> preguntasParaAdopciones) {
        this.preguntasParaAdopcion = preguntasParaAdopciones;
        this.ubicacionAsociacion = ubicacion;
    }

    public Asociacion() {

    }

    public List<PublicacionSinChapita> listarPublicacionesDeMascotasPerdidasSinChapita() {
        return publicacionesSinChapita.stream().filter(publicacion -> publicacion.esPublicacionAprobada()).collect(Collectors.toList());
    }

    public List<PublicacionConChapita> listarPublicacionesDeMascotasPerdidasConChapita() {
        return publicacionesConChapita.stream().filter(publicacion -> publicacion.esPublicacionAprobada()).collect(Collectors.toList());
    }

    public List<PublicacionSinChapita> listarPublicacionesMascotasPerdidasSinChapitaUltimosDias(int dias) {
        LocalDate hoy = LocalDate.now();
        return publicacionesSinChapita.stream().filter(p -> p.getMascotaPerdida().getFecha().isAfter(hoy.minusDays(dias))).collect(Collectors.toList());

    }
/*
    public List<Pregunta> getPreguntasParaFormularioDeAdopcion(RepositorioPreguntas repositorioPreguntas){
        List<Pregunta> preguntas = new ArrayList<>();
          Metodo para listar todas las preguntas opcionales y obligatorias (del repo)
          Es hacer el merge de las preguntas
          Pero, preguntasParaAdopcion ya tiene ambas preguntas? (opcionales y obligatorias)
        return preguntas;
    }
*/
    public void agregarPublicacionMascotaSinChapita(PublicacionSinChapita publicacion) {
        publicacionesSinChapita.add(publicacion);
    }

    public void agregarPublicacionMascotaConChapita(PublicacionConChapita publicacion) {
        publicacionesConChapita.add(publicacion);
    }

    public void agregarPublicacionParaAdoptar(PublicacionParaAdoptar publicacion) {
        publicacionesParaAdoptar.add(publicacion);
    }

    public void agregarPublicacionParaDarEnAdopcion(PublicacionParaDarEnAdopcion publicacion) {
        publicacionesParaDarEnAdopcion.add(publicacion);
    }

    public void agregarPreguntasParaAdopcion(Pregunta pregunta){
        preguntasParaAdopcion.add(pregunta);
    }

    public void quitarPreguntasParaAdopcion(Pregunta pregunta){
        preguntasParaAdopcion.remove(pregunta);
    }

    public void quitarPublicacionParaAdoptar(PublicacionParaAdoptar publicacion) {
        publicacionesParaAdoptar.remove(publicacion);
    }

    public void quitarPublicacionParaDarEnAdopcion(PublicacionParaDarEnAdopcion publicacion){
        publicacionesParaDarEnAdopcion.remove(publicacion);
    }

    //Getters necesarios para el mvn verify

    public Ubicacion getUbicacionAsociacion() {
        return ubicacionAsociacion;
    }

    public List<PublicacionSinChapita> getPublicacionesSinChapita() {
        return publicacionesSinChapita;
    }

    public List<PublicacionConChapita> getPublicacionesConChapita() {
        return publicacionesConChapita;
    }

    public List<PublicacionParaAdoptar> getPublicacionesParaAdoptar() {
        return publicacionesParaAdoptar;
    }

    public List<PublicacionParaDarEnAdopcion> getPublicacionesParaDarEnAdopcion() {
        return publicacionesParaDarEnAdopcion;
    }

    public Long getIdAsociacion(){return idAsociacion;}

}
