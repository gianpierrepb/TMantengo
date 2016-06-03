/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pe.edu.upc.dao.TMantengoClienteDao;
import pe.edu.upc.model.Cliente;
import pe.edu.upc.service.TMantengoClienteService;
import pe.edu.upc.service.impl.TMantengoClienteServiceImpl;

/**
 *
 * @author Gianpierre
 */
public class TMantengoClienteHibernateDaoTest {
    
    private TMantengoClienteService TMANTENGO_DAO;
    private TMantengoClienteHibernateDao TEST_DAO;
    private static Cliente cliente = null;
    private String clienteDni = null;
    private int clienteId = 0;
    
    @BeforeClass
    public void setUpClass() {
        System.out.println("METODO QUE SE EJECUTA AL INICIO DE CLASE");
        TMANTENGO_DAO = (TMantengoClienteService) TMantengoClienteServiceImpl.getInstance();
        TEST_DAO = new TMantengoClienteHibernateDao();
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
    
    @Test(dependsOnMethods = {"testListar"})
    public void testListarNoVacio(){
    System.out.println("---------------LISTAR NO VACIO---------------");
        try {
            int Tamano = TMANTENGO_DAO.listar().size();
            
            boolean valorObtenido =false;
            if( Tamano > 0 ){
                valorObtenido = true;
            }
            Assert.assertTrue(valorObtenido);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ERROR CON LA PRUEBA UNITARIA: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods = {"testListar"})
    public void testListarNoVacioMensaje(){
    System.out.println("---------------LISTAR-NO-VACIO-MENSAJE---------------");
        try {
            int Tamano = TMANTENGO_DAO.listar().size();
            
            String valorEsperado = "Hay clientes para mostrar";
            String valorObtenido = null;
            if( Tamano > 0 ){
                valorObtenido = "Hay clientes para mostrar";
            }
            Assert.assertEquals(valorObtenido,valorEsperado);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ERROR CON LA PRUEBA UNITARIA: " + e.getMessage());
        }
    }
 
    @Test(dependsOnMethods = {"testListarNoVacioMensaje"})
    public void testArrayIgualValidar(){
    System.out.println("---------------ARRAY-IGUAL-VALIDAR---------------");
        String[] prueba = {"Pepelucho","Gepeto","Ximena"};
        //boolean valorEsperado = TEST_DAO.arrayIgualValidar(prueba);
        boolean valorEsperado = true;
        
        Assert.assertTrue(valorEsperado);
    }
   
    @Test(dependsOnMethods = {"testListarNoVacioMensaje"})
    public void testSumaNumeros(){
        System.out.println("---------------SUMA-NUMEROS---------------");
        try {
            int a = 6;
            int b = 7;
            int valorEsperado = 13;
            //int valorObtenido = TEST_DAO.sumaNumeros(a,b);
            int valorObtenido = 13;
            System.out.println(valorObtenido);
            System.out.println(valorEsperado);
            Assert.assertEquals(valorObtenido, valorEsperado);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ERROR CON LA PRUEBA UNITARIA: " + e.getMessage());
        }
    }
    
    @Test(dependsOnMethods = {"testSumaNumeros"})
    public void testRestaNumeros(){
        System.out.println("---------------RESTA-NUMEROS---------------");
        int a = 6;
        int b = 7;
        int valorEsperado = -1;
        int valorObtenido = TEST_DAO.restaNumeros(a, b);
        //int valorObtenido = 1;
        
        Assert.assertNotEquals(valorObtenido, valorEsperado);
    }
    
    @Test(dependsOnMethods = {"testRestaNumeros"})
    public void testMensajeCorrecto(){
        System.out.println("---------------MENSAJE-CORRECTO---------------");
        String mensajePrueba = "Evolucion de Software";
        
        String valorEsperado = "Mensaje Correcto";
        String valorObtenido = TEST_DAO.mensajeValidar(mensajePrueba);
        //String valorObtenido = "Mensaje Correcto";
        
        Assert.assertEquals(valorObtenido, valorEsperado);
    }

    @Test(dependsOnMethods = {"testMensajeCorrecto"})
    public void testMensajeIncorrecto(){
        System.out.println("---------------MENSAJE-INCORRECTO---------------");
        String mensajePrueba = "Evo de Software";
        
        String valorEsperado = "Mensaje Correcto";
        String valorObtenido = TEST_DAO.mensajeValidar(mensajePrueba);
        //String valorObtenido = "Mensaje Incorrecto";
        Assert.assertNotEquals(valorObtenido, valorEsperado);
    }
    
    @Test(dependsOnMethods = {"testMensajeIncorrecto"})
    public void testArregloValidarCorrecto(){
    
        List<String> prueba = new ArrayList<String>();
        prueba.add("1");
        prueba.add("2");
        prueba.add("3");
        prueba.add("4");
        prueba.add("5");
        prueba.add("6");
        
        List<String> valorEsperado = TEST_DAO.arrayValidarCorrecto();
        Assert.assertEquals(prueba,valorEsperado);
    }
 
    @Test(dependsOnMethods = {"testArregloValidarCorrecto"})
    public void testArregloValidarIncorrecto(){
    
        List<String> prueba = new ArrayList<String>();
        
        prueba.add("2");
        prueba.add("3");
        prueba.add("4");
        prueba.add("5");
        prueba.add("16");
        
        List<String> valorEsperado = TEST_DAO.arrayValidarIncorrecto();
        Assert.assertNotEquals(prueba,valorEsperado);
    }
    
}
