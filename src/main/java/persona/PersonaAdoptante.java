package persona;

import javax.persistence.*;

//TODO: Evaluar si esta clase al final tenía sentido
@Entity
//@DiscriminatorValue("PA")
public class PersonaAdoptante{

  @Id
  @GeneratedValue
  public Long idPersonaAdoptante;

  @OneToOne(cascade = CascadeType.PERSIST)
  private Persona persona;

  public PersonaAdoptante(Persona persona) {
    this.persona = persona;
  }

  public PersonaAdoptante() {

  }
}
