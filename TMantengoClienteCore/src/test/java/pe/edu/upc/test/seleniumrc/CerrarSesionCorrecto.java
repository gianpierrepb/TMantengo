package pe.edu.upc.test.seleniumrc;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class CerrarSesionCorrecto {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.start();
	}

	@Test
	public void testCerrarSesionCorrecto() throws Exception {
		selenium.open("/TMantengoClienteWeb/iniciarSesion.jsp");
		assertEquals("Login", selenium.getText("css=strong"));
		selenium.type("id=txtUsuario", "root");
		selenium.type("id=txtPassword", "root");
		selenium.click("id=btnIniciarSesion");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=linkCerrarSesion");
		assertTrue(selenium.getConfirmation().matches("^¿Seguro que desea cerrar sesion[\\s\\S]$"));
		selenium.waitForPageToLoad("300000");
                assertEquals("Login", selenium.getText("css=strong"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
