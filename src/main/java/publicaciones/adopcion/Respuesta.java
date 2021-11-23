package publicaciones.adopcion;

import exceptions.RespuestaInvalidaException;

import javax.persistence.*;
import java.util.List;

@Entity
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRespuesta;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Pregunta pregunta;

    private String valor;

    public Respuesta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Respuesta() {

    }

    public void validar(String respuesta){
        if(pregunta.getOpciones().stream().anyMatch(o -> o.getOpcion().equals(respuesta))) {
            this.valor = respuesta;
        }else{
            throw new RespuestaInvalidaException("La respuesta no se encuentra dentro de las opciones");
        }
    }

    public boolean tieneMismoValor(List<Respuesta> otrasRespuestas){
        return otrasRespuestas.stream()
            .anyMatch(respuesta -> respuesta.laPreguntaEsLaMisma(this)
                                    && respuesta.laRespuestaEsLaMisma(this));
    }

    private boolean laRespuestaEsLaMisma(Respuesta otraRespuesta) {
        return valor.equals(otraRespuesta.getValor());
    }

    private boolean laPreguntaEsLaMisma(Respuesta otraRespuesta) {
        return getPregunta().getPreguntaParaAdoptar().equals(otraRespuesta.getPregunta().getPreguntaParaAdoptar())
            && getPregunta().getPreguntaParaDarEnAdopcion().equals(otraRespuesta.getPregunta().getPreguntaParaDarEnAdopcion());
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public String getValor(){
        return valor;
    }
}
