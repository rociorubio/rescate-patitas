package publicaciones.adopcion;

import javax.persistence.*;

@Entity
public class Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOpcion;

    private String opcion;

    public Opcion(String opcion) {
        this.opcion = opcion;
    }

    public Opcion() {}

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        if(opcion == null || opcion.equals("")){
            throw new RuntimeException("Opcion inválida");
        }
        this.opcion = opcion;
    }

    public Long getIdOpcion(){
        return idOpcion;
    }
}
