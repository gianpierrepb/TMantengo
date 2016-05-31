package pe.edu.upc.test.seleniumrc;


import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class RegistrarClienteCorrecto {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.start();
	}

	@Test
	public void testRegistrarClienteCorrecto() throws Exception {
		selenium.open("/TMantengoClienteWeb/iniciarSesion.jsp");
		selenium.type("id=txtUsuario", "root");
		selenium.type("id=txtPassword", "root");
		selenium.click("id=btnIniciarSesion");
		selenium.waitForPageToLoad("30000");
		assertEquals("Lista de clientes", selenium.getText("css=h1.page-header"));
		selenium.click("id=btnAgregar");
		selenium.waitForPageToLoad("30000");
		assertEquals("Administrar Clientes", selenium.getText("css=h1.page-header"));
		selenium.type("id=txtNombre", "Victor");
		selenium.type("id=txtApellido", "Aguilar");
		selenium.type("id=txtDni", "71213890");
		selenium.type("name=txtEdad", "20");
		selenium.click("xpath=(//input[@id='rdoSexo'])[2]");
		selenium.select("id=cboEstudios", "label=Universitario");
		selenium.type("name=txtTelefono", "2910534");
		selenium.click("id=btnRegistrar");
		assertTrue(selenium.getConfirmation().matches("^¿Está seguro de registrar este cliente[\\s\\S]$"));
		assertEquals("Clientes registrados", selenium.getText("css=h3.panel-title"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
