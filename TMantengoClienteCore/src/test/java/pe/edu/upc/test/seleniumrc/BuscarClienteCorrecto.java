package pe.edu.upc.test.seleniumrc;


import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class BuscarClienteCorrecto {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.start();
	}

	@Test
	public void testBuscarClienteCorrecto() throws Exception {
		selenium.open("/TMantengoClienteWeb/iniciarSesion.jsp");
		selenium.type("id=txtUsuario", "root");
		selenium.type("id=txtPassword", "root");
		selenium.click("id=btnIniciarSesion");
		selenium.waitForPageToLoad("30000");
		assertEquals("Lista de clientes", selenium.getText("css=h1.page-header"));
		assertEquals("", selenium.getText("id=searchInput"));
		selenium.type("id=searchInput", "VICTOR");
		assertEquals("VICTOR", selenium.getText("//tbody[@id='toBody']/tr/td[2]"));
		assertEquals("Editar", selenium.getText("id=modificar1"));
		assertEquals("Eliminar", selenium.getText("id=eliminar1"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
