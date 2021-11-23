package validadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ValidadorContrasenia {

  private List<Validador> validadores;

  public ValidadorContrasenia(Validador ... _validadores) {
    validadores = new ArrayList<>();
    Collections.addAll(this.validadores, _validadores);
  }

  public void agregar(Validador ... _validadores){
    Collections.addAll(this.validadores, _validadores);
  }

  public void esContraseniaValida(String contrasenia){
    validadores.forEach(validador -> validador.validar(contrasenia));
  }

}
