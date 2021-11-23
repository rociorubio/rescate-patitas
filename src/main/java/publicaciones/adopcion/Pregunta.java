package publicaciones.adopcion;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPregunta;

    private String preguntaParaDarEnAdopcion;
    private String preguntaParaAdoptar;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Opcion> opciones;

    public Pregunta(String preguntaParaDarEnAdopcion, String preguntaParaAdoptar, List<Opcion> opciones) {
        this.preguntaParaDarEnAdopcion = preguntaParaDarEnAdopcion;
        this.preguntaParaAdoptar = preguntaParaAdoptar;
        this.opciones = opciones;
    }

    public Pregunta() {

    }

    public String getPreguntaParaDarEnAdopcion() {
        return preguntaParaDarEnAdopcion;
    }

    public String getPreguntaParaAdoptar() {
        return preguntaParaAdoptar;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setPreguntaParaDarEnAdopcion(String nuevaPregunta) {
        this.preguntaParaDarEnAdopcion = nuevaPregunta;
    }
}

