package validadores;

import exceptions.ContraseniaInvalidaException;

public abstract class Validador {

    public void validar(String contrasenia){
        if(! this.validarContrasenia(contrasenia)){
            throw new ContraseniaInvalidaException(this.descripcion());
        }
    }

    public abstract boolean validarContrasenia(String contrasenia);
    public abstract String descripcion();
}
