package persona.usuario;

import org.mindrot.jbcrypt.BCrypt;

public class Hasheador {
	private static final Integer COMPLEJIDAD = 8;

	public static String hashear(String contrasenia) {
		return BCrypt.hashpw(contrasenia, BCrypt.gensalt(COMPLEJIDAD));
	}

	public static boolean deshashear(String contrasenia, String hash) { return BCrypt.checkpw(contrasenia, hash); }
}
