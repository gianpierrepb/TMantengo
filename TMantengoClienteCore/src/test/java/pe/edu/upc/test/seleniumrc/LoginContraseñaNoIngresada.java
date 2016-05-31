package pe.edu.upc.test.seleniumrc;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class LoginContraseñaNoIngresada {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.start();
	}

	@Test
	public void testLoginContraseñaNoIngresada() throws Exception {
		selenium.open("/TMantengoClienteWeb/");
		assertEquals("Login", selenium.getText("css=strong"));
		selenium.type("id=txtUsuario", "root");
		selenium.click("id=btnIniciarSesion");
		selenium.waitForPageToLoad("30000");
		assertEquals("Por favor, ingrese valores en los campos vacios.", selenium.getText("id=mensajeRequired"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
