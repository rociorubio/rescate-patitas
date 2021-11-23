package publicaciones.adopcion;

import mascota.Mascota;
import persona.DuenioMascota;
import persona.Persona;

import javax.persistence.*;
import java.util.List;

@Entity
public class PublicacionParaDarEnAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacionParaDarEnAdopcion;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Mascota mascota;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Respuesta> respuestas; //No sabemos como se completan, pero se completan

    public PublicacionParaDarEnAdopcion(List<Respuesta> respuestas, Mascota mascota) {
        this.respuestas = respuestas;
        this.mascota = mascota;
    }

    public PublicacionParaDarEnAdopcion() {

    }

    public void notificarDuenioPersonaInteresada(Persona persona) {
        DuenioMascota duenio = mascota.getDuenioMascota();
        String descripcionDeContacto = persona.getDescripcionDeContacto();
        duenio.notificar("Hay una persona interesada en adoptar a tu mascota!" + descripcionDeContacto);
    }

    public Mascota getMascota() {
        return mascota;
    }

    public List<Respuesta> getRespuestas(){
        return respuestas;
    }

    public void agregarRespuesta(Respuesta respuesta){
        respuestas.add(respuesta);
    }
}

