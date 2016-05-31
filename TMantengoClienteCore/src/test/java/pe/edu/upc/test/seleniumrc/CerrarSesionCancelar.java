package pe.edu.upc.test.seleniumrc;


import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class CerrarSesionCancelar {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.start();
	}

	@Test
	public void testCerrarSesionCancelar() throws Exception {
		selenium.open("/TMantengoClienteWeb/iniciarSesion.jsp");
		selenium.type("id=txtUsuario", "root");
		selenium.type("id=txtPassword", "root");
		selenium.click("id=btnIniciarSesion");
		selenium.waitForPageToLoad("30000");
		assertEquals("Lista de clientes", selenium.getText("css=h1.page-header"));
		selenium.chooseCancelOnNextConfirmation();
		selenium.click("css=i.fa.fa-sign-out");
		assertTrue(selenium.getConfirmation().matches("^¿Seguro que desea cerrar sesion[\\s\\S]$"));
		assertEquals("Lista de clientes", selenium.getText("css=h1.page-header"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
