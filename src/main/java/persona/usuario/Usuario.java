package persona.usuario;

import exceptions.UsuarioInvalidoException;
import validadores.ValidadorContrasenia;

import javax.persistence.*;

@Entity
public class Usuario {

  @Id
  @GeneratedValue
  private long idUsuario;

  private String user;

  private String contrasenia;

  @Enumerated(EnumType.STRING)
  private TipoUsuario tipoUsuario;

  //TODO: Fijarse si acá esta bien poner el validador
  @Transient
  private ValidadorContrasenia validadorContrasenia;

  public Usuario(String user, String contrasenia, TipoUsuario tipoUsuario, ValidadorContrasenia validadorContrasenia){
    if(user == null || contrasenia == null || tipoUsuario == null){
      throw new UsuarioInvalidoException("ERROR: Usuario invalido");
    }
    this.user = user;
    this.validadorContrasenia = validadorContrasenia;
    esValida(contrasenia);
    this.contrasenia = Hasheador.hashear(contrasenia);
    this.tipoUsuario = tipoUsuario;

  }

  public Usuario() {

  }

  public void esValida(String contrasenia){
    validadorContrasenia.esContraseniaValida(contrasenia);
  }

  public String getPassword(){return contrasenia;}
  public String getUsername() {return user;}
  public TipoUsuario getTipoUsuario() {return tipoUsuario;}
  public String getContrasenia() {return contrasenia;}

  public Long getIdUsuario() {
    return idUsuario;
  }
}
