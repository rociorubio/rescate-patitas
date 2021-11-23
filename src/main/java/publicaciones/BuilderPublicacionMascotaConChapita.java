package publicaciones;

import mascota.Asociacion;
import mascota.Mascota;
import mascota.MascotaPerdida;
import persona.Rescatista;
import repositorios.RepositorioAsociaciones;
import repositorios.RepositorioMascotas;

public class BuilderPublicacionMascotaConChapita {

    private Rescatista rescatista;
    private MascotaPerdida mascotaPerdida;
    private RepositorioAsociaciones repositorioAsociaciones;
    private RepositorioMascotas repositorioMascotas;

    public void rescatista(Rescatista rescatista) {
        this.rescatista = rescatista;
    }

    public void mascotaEncontrada(MascotaPerdida mascotaPerdida){
        this.mascotaPerdida = mascotaPerdida;
    }

    public void setRepositorioAsociaciones(RepositorioAsociaciones repositorioAsociaciones){
        this.repositorioAsociaciones = repositorioAsociaciones;
    }

    public void setRepositorioMascotas(RepositorioMascotas repositorioMascotas){
        this.repositorioMascotas = repositorioMascotas;
    }
    public PublicacionConChapita build() {
        Asociacion asociacion = repositorioAsociaciones.asociacionAcorde(mascotaPerdida);
        Mascota mascota = repositorioMascotas.mascotaEncontradaEstaRegistrada(mascotaPerdida);

        PublicacionConChapita publicacion = new PublicacionConChapita(rescatista, mascota);
        asociacion.agregarPublicacionMascotaConChapita(publicacion);
        return publicacion;
        }
}
