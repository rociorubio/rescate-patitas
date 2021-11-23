import main.Routes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class mainTest {
	Routes r = new Routes();

	@Test
	public void sePuedeCorrerMain(){
		String[] args = new String[100];
		r.main(args);
	}
}
