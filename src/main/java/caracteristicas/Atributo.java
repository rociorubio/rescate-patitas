package caracteristicas;

import exceptions.AtributoInvalidoException;

import javax.persistence.*;

@Entity
public class Atributo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtributo;

    @OneToOne(cascade = CascadeType.ALL)
    private Caracteristica caracteristica;

    private String valor;

    public Atributo(Caracteristica caracteristica, String valor) {
        if(!caracteristica.validarOpcion(valor)) throw new AtributoInvalidoException("El atributo ingresado no corresponde a su caracteristica");

        this.caracteristica = caracteristica;
        this.valor = valor;
    }

    public Atributo() {

    }

    public Caracteristica getCaracteristica() { return caracteristica; }
    public String getValor() { return valor; }

}