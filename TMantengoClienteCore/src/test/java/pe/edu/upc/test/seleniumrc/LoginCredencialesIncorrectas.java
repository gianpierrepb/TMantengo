package pe.edu.upc.test.seleniumrc;
import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class LoginCredencialesIncorrectas {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.start();
	}

	@Test
	public void testLoginCredencialesIncorrectas() throws Exception {
		selenium.open("/TMantengoClienteWeb/iniciarSesion.jsp?messageRequired=Por%20favor,%20ingrese%20valores%20en%20los%20campos%20vacios.");
		assertEquals("Login", selenium.getText("css=strong"));
		selenium.type("id=txtUsuario", "rut");
		selenium.type("id=txtPassword", "rut");
		selenium.click("id=btnIniciarSesion");
		selenium.waitForPageToLoad("30000");
		assertEquals("Valores no validos.", selenium.getText("id=mensajeRequired"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
