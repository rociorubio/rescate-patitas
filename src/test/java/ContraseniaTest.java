import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import exceptions.ContraseniaInvalidaException;
import validadores.Validador;
import validadores.ValidadorContenido;
import validadores.ValidadorDeLongitud;
import validadores.ValidadorEnBlackList;

import static org.junit.jupiter.api.Assertions.*;

public class ContraseniaTest {

  Validador validadorLongitud = new ValidadorDeLongitud(8,64);
  Validador validadorContenido = new ValidadorContenido();
  Validador validadorEnBlackList = new ValidadorEnBlackList();

  private String contraseniaCorta = "Il3";
  private String contraseniaLarga = "qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop";
  private String contraseniaEnBlacklist = "Fortune12";
  private String contraseniaSinMayuscula = "alam890sd";
  private String contraseniaSinMinuscula = "MANSKDE2321";
  private String contraseniaSinNumeros = "MASDASENasnd";
  private String contraseniaValida = "asdaMENA12314";

 @Test
  public void contraseniaMenorDeOchoCaracteresNoEsValida(){
   assertThrows(ContraseniaInvalidaException.class, () -> validadorLongitud.validar(contraseniaCorta));
  }

  @Test
  public void contraseniaMayorDe64CaracteresNoEsValida(){
    assertThrows(ContraseniaInvalidaException.class, () -> validadorLongitud.validar(contraseniaLarga));
  }

  @Test
  public void contraseniaEnBlacklistNoEsValida(){
    assertThrows(ContraseniaInvalidaException.class, () -> validadorEnBlackList.validar(contraseniaEnBlacklist));
  }

  @Test
  public void contraseniasSinMayusculaNoSonValidas(){
    assertThrows(ContraseniaInvalidaException.class, () ->validadorContenido.validar(contraseniaSinMayuscula));
  }

  @Test
  public void contraseniasSinMinusculaNoSonValidas(){
    assertThrows(ContraseniaInvalidaException.class, () -> validadorContenido.validar(contraseniaSinMinuscula));
  }

  @Test
  public void contraseniasSinNumeroNoSonValidas(){
    assertThrows(ContraseniaInvalidaException.class, () -> validadorContenido.validar(contraseniaSinNumeros));
  }

  @Test
  public void contraseniaConLongitudValidaNoEnBlacklistYComplejaEsValida(){
    validadorContenido.validar(contraseniaValida);
  }

}
