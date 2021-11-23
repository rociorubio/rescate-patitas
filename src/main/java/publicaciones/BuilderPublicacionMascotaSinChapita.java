package publicaciones;

import mascota.Asociacion;
import mascota.MascotaPerdida;
import persona.Rescatista;
import repositorios.RepositorioAsociaciones;

public class BuilderPublicacionMascotaSinChapita {

    private Rescatista rescatista;
    private MascotaPerdida mascotaPerdida;
    private RepositorioAsociaciones repositorioAsociaciones;

    public void rescatista(Rescatista rescatista){
        this.rescatista = rescatista;
    }

    public void mascotaEncontrada(MascotaPerdida mascotaPerdida){
        this.mascotaPerdida = mascotaPerdida;
    }

    public void setRepositorioMascotas(RepositorioAsociaciones repositorioAsociaciones){
        this.repositorioAsociaciones = repositorioAsociaciones;
    }

    public PublicacionSinChapita build() {
        Asociacion asociacion = repositorioAsociaciones.asociacionAcorde(mascotaPerdida);

        PublicacionSinChapita publicacion = new PublicacionSinChapita(rescatista, mascotaPerdida);
        asociacion.agregarPublicacionMascotaSinChapita(publicacion);
        return publicacion;
    }
}
