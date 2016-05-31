/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upc.dao.impl;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pe.edu.upc.model.Cliente;
import pe.edu.upc.service.TMantengoClienteService;
import pe.edu.upc.service.impl.TMantengoClienteServiceImpl;

/**
 *
 * @author Gianpierre
 */
public class TMantengoClienteHibernateDaoTest {
    
    private TMantengoClienteService TMANTENGO_DAO;
    private static Cliente cliente = null;
    private String clienteDni = null;
    private int clienteId = 0;
    
    @BeforeClass
    public void setUpClass() {
        System.out.println("METODO QUE SE EJECUTA AL INICIO DE CLASE");
        TMANTENGO_DAO = (TMantengoClienteService) TMantengoClienteServiceImpl.getInstance();
    }
    
    @AfterClass
    public void tearDownClass() {
        System.out.println("METODO QUE SE EJECUTA AL FINAL DE CLASE");
    }
    
    @BeforeMethod
    public void tearDownMethod() {
        System.out.println("METODO QUE SE EJECUTA AL INICO DE CADA METODO");
    }
    
    @AfterMethod
    public void setUpMethod() {
        System.out.println("METODO QUE SE EJECUTA AL FINAL DE CADA METODO");
    }

   
    @Test
    public void testInsertar() throws Exception {
        System.out.println("---------------INSERTAR---------------");
            try{     
            //ENTRADA
            cliente = new Cliente();
            cliente.setApellido("PRUEBA");
            cliente.setNombre("PRUEBA");
            cliente.setDni("12345678");
            cliente.setEdad("24");
            cliente.setSexo("MASCULINO");
            cliente.setNivelEstudios("UNIVERSITARIO");
            cliente.setTelefono("987674372");
            //PROCESO
            TMANTENGO_DAO.insertar(cliente);
            clienteDni = cliente.getDni();//se usará en pruebas posteriores
            clienteId = cliente.getIdCliente();//se usará en pruebas posteriores
            //CONFIRMACION
            Assert.assertTrue(cliente.getIdCliente()>0);
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail("NO SE EJECUTO CON EXITO LA PRUEBA UNITARIA "+e.getMessage());
        }
    }
    
    @Test(dependsOnMethods = {"testInsertar"})//se especifica el orden de ejecución de las pruebas
    public void testInsertarIncorrecto() {
        System.out.println("---------------INSERTAR-INCORRECTO---------------");
        try {
            Cliente clienteNoValido = new Cliente();
            clienteNoValido.setNombre("PRUEBA");
            clienteNoValido.setApellido("PRUEBA");
            clienteNoValido.setDni(clienteDni);
            clienteNoValido.setEdad("24");
            clienteNoValido.setSexo("MASCULINO");
            clienteNoValido.setNivelEstudios("UNIVERSITARIO");
            clienteNoValido.setTelefono("987674372");

            TMANTENGO_DAO.insertar(clienteNoValido);
            Assert.fail("ERROR CON LA PRUEBA UNITARIA");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(true);
        }
    }
    
    @Test(dependsOnMethods = {"testInsertarIncorrecto"})
    public void testObtenerIncorrecto(){
        System.out.println("---------------OBTENER-INCORRECTO---------------");
        try{
            cliente = TMANTENGO_DAO.obtener(null);
            Assert.fail("ERROR CON LA PRUEBA UNITARIA");
        } catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue(true);
        }
    }
    
    @Test(dependsOnMethods = {"testObtenerIncorrecto"})
    public void testObtener() {
        System.out.println("---------------OBTENER---------------");
        try {
            cliente = TMANTENGO_DAO.obtener(clienteId);
            
            System.out.println(cliente.getNombre());
            System.out.println(cliente.getApellido());
            System.out.println(cliente.getDni());
            System.out.println(cliente.getEdad());
            System.out.println(cliente.getSexo());
            System.out.println(cliente.getNivelEstudios());
            System.out.println(cliente.getTelefono());
            Assert.assertTrue(cliente != null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ERROR CON LA PRUEBA UNITARIA: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods = {"testObtener"})
    public void testActualizar() {
        System.out.println("---------------ACTUALIZAR---------------");
        try {
            cliente.setNombre("PRUEBAupdate");
            cliente.setApellido("PRUEBAupdate");

            TMANTENGO_DAO.actualizar(cliente);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ERROR CON LA PRUEBA UNITARIA: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods = {"testActualizar"})
    public void testActualizarIncorrecto() {
        System.out.println("---------------ACTUALIZAR-INCORRECTO---------------");
        try {
            cliente.setClienteId(null);
            cliente.setNombre("PRUEBAnewupdate");
            cliente.setApellido("PRUEBAnewupdate");

            TMANTENGO_DAO.actualizar(cliente);
            Assert.fail("ERROR CON LA PRUEBA UNITARIA");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(true);
        }
    }
    
    @Test(dependsOnMethods = {"testActualizarIncorrecto"})
    public void testListar() {
        System.out.println("---------------LISTAR---------------");
        try {
            List<Cliente> lista = TMANTENGO_DAO.listar();
            Assert.assertTrue(lista.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ERROR CON LA PRUEBA UNITARIA: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods = {"testListar"})
    public void testEliminar() {
        System.out.println("---------------ELIMINAR---------------");
        try {
            TMANTENGO_DAO.eliminar(clienteId);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ERROR CON LA PRUEBA UNITARIA: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = {"testEliminar"})
    public void testEliminarIncorrecto() {
        System.out.println("---------------ELIMINAR-INCORRECTO---------------");
        try {
            TMANTENGO_DAO.eliminar(clienteId);
            Assert.fail("ERROR CON LA PRUEBA UNITARIA");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(true);
        }
    }
}
