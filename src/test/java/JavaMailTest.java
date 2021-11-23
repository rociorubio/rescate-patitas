import mensajeria.TwilioSMS;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import mensajeria.JavaMail;
import static org.mockito.Mockito.*;

public class JavaMailTest {
    JavaMail javaMailmock = mock(JavaMail.class);
    TwilioSMS twilioSMSmock = mock(TwilioSMS.class);
    JavaMail javaMail = new JavaMail();
    TwilioSMS twilioSMS = new TwilioSMS();

    @BeforeEach
    void initJavaMail() { }

    @Test
    public void SePuedeEnviarMail() throws Exception {
        javaMailmock.enviar();
        javaMail.enviar();
        verify(javaMailmock, times(1)).enviar();
    }

    @Test
    public void sePuedeEnviarSMS(){
        twilioSMSmock.enviar();
        twilioSMS.enviar();
        verify(twilioSMSmock, times(1)).enviar();
    }
}
