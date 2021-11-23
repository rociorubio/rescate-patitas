package publicaciones.adopcion;

import persona.Persona;

import javax.persistence.*;
import java.util.List;

@Entity
public class PublicacionParaAdoptar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacionParaAdoptar;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Persona persona;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Respuesta> respuestas; //No sabemos como se completan, pero se completan

    public PublicacionParaAdoptar(List<Respuesta> respuestas, Persona persona) {
        this.persona = persona;
        this.respuestas = respuestas;
    }

    public PublicacionParaAdoptar() {

    }

    public boolean tieneMismaRespuestas(List<Respuesta> otrasRespuestas){ //Las respuestas son las de la publicacion para dar en adopcion, en principio
        respuestas.stream().allMatch(respuesta -> {
            return respuesta.tieneMismoValor(otrasRespuestas);
        });
        return true;
    }

    public Persona getPersona() {
        return persona;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void agregarRespuesta(Respuesta respuesta){
        respuestas.add(respuesta);
    }
}
