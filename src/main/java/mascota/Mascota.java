package mascota;

import caracteristicas.Atributo;
import exceptions.MascotaInvalidaException;
import persona.DuenioMascota;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;

    private String nombre;
    private String apodo;
    private Integer edad;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Atributo> atributos;

    @Column(name="tipoMascota", nullable = false, length = 8 )
    @Enumerated(value = EnumType.STRING)
    private TipoMascota tipoMascota;

    @Enumerated(value = EnumType.STRING)
    private SexoMascota sexo;

    private String fotos;
    private String chapita;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idDuenioMascota")
    private DuenioMascota duenioMascota;

    public Mascota(String nombre, String apodo, Integer edad, List<Atributo> atributos, TipoMascota tipoMascota, SexoMascota sexo, String fotos, String chapita, DuenioMascota duenio) {
        if (nombre == null || apodo == null || edad == null || atributos == null || tipoMascota == null || sexo == null || chapita == null)
            throw new MascotaInvalidaException("Controle los datos ingresados");

        this.nombre = nombre;
        this.apodo = apodo;
        this.edad = edad;
        this.atributos = atributos;
        this.tipoMascota = tipoMascota;
        this.sexo = sexo;
        this.fotos = fotos;
        this.chapita = chapita;
        this.duenioMascota = duenio;
    }

    public Mascota() {

    }

    public void agregarAtributo(Atributo atributo){ atributos.add(atributo); }
    public DuenioMascota getDuenioMascota() {
        return duenioMascota;
    }
    public String getChapita() {return chapita;}
    public List<Atributo> getAtributos() { return atributos; }
    public void setAtributos(ArrayList<Atributo> atributos) { this.atributos = atributos; }
    public String getNombre() { return nombre; }
    public String getApodo() { return apodo; }
    public Integer getEdad() { return edad; }
    public TipoMascota getTipoMascota() { return tipoMascota; }
    public SexoMascota getSexo() { return sexo; }
    public String getFotos() { return fotos; }
    public Long getIdMascota(){return idMascota;}
}