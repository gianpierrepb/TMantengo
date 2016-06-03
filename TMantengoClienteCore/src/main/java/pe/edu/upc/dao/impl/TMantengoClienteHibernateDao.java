package pe.edu.upc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pe.edu.upc.dao.TMantengoClienteDao;
import pe.edu.upc.dao.base.BaseHibernateDao;
import pe.edu.upc.model.Cliente;

public class TMantengoClienteHibernateDao extends BaseHibernateDao implements TMantengoClienteDao {

    private static final TMantengoClienteHibernateDao CLIENTE_DAO_IMPL;

    static {
        CLIENTE_DAO_IMPL = new TMantengoClienteHibernateDao();
    }

    public TMantengoClienteHibernateDao() {
    }

    public static TMantengoClienteHibernateDao getInstance() {
        return CLIENTE_DAO_IMPL;
    }
    
    Cliente clienteRetorno = new Cliente();
    
    @Override
    public void insertar(Cliente entity) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = obtenerSesion();
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            cerrarSesion(session);
        }
    }

    @Override
    public void actualizar(Cliente entity) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = obtenerSesion();
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            cerrarSesion(session);
        }
    }

    @Override
    public void eliminar(Integer entityId) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = obtenerSesionActual();
            tx = session.beginTransaction();
            session.delete(session.get(Cliente.class, entityId));
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            cerrarSesion(session);
        }
    }

    @Override
    public Cliente obtener(Integer entityId) throws Exception {
        Session session = null;
        Cliente cliente = null;
        try {
            session = obtenerSesion();
            session.beginTransaction();
            cliente = (Cliente) session.get(Cliente.class, entityId);
        } catch (Exception ex) {
            throw ex;
        } finally {
            cerrarSesion(session);
        }
        return cliente;
    }

    @Override
    public List<Cliente> listar() throws Exception {
        Session session = null;
        List<Cliente> lista = null;
        try {
            session = obtenerSesion();
            session.beginTransaction();
            String hql = "FROM Cliente c";
            Query query = session.createQuery(hql);
            lista = query.list();
        } catch (Exception ex) {
            throw ex;
        } finally {
            cerrarSesion(session);
        }
        return lista;
    }

    @Override
    public Cliente obtenerClientePorDni(String dni) throws Exception {
        Session session = null;
        Cliente cliente = null;
        try {
            session = obtenerSesion();
            session.beginTransaction();
            String nombreSentencia = "Cliente.findByDni";
            Query query = session.getNamedQuery(nombreSentencia);
            query.setParameter("dni", dni);
            cliente = (Cliente) query.uniqueResult();
        } catch (Exception ex) {
            throw ex;
        } finally {
            cerrarSesion(session);
        }
        return cliente;
    }
    
    public Boolean arrayIgualValidar (String[] nombres2){

        String[] nombres1 = {"Pepelucho","Gepeto","Ximena"};

        if (nombres2 != nombres1)
        {
            return false;
        }
        return true;
    }

    public int sumaNumeros(int a, int b){
        
        Integer suma;
        suma = a + b;
        
        return suma;
    }
    
    public int restaNumeros(int a, int b){
        
        int resta;
        resta = b - a;
        
        return resta;
    }
    
    public String mensajeValidar( String mensaje){
        
        String mensajeValido = "Evolucion de Software";
        
        if(mensajeValido != mensaje)
            return "Mensaje Incorrecto";
        return "Mensaje Correcto";
    }
    
    public List<String> arrayValidarCorrecto(){
        
        List<String> miLista = new ArrayList<String>();
        miLista.add("1");
        miLista.add("2");
        miLista.add("3");
        miLista.add("4");
        miLista.add("5");
        miLista.add("6");
         
        return miLista;
    }
    
    public List<String> arrayValidarIncorrecto(){
    
        List<String> miLista = new ArrayList<String>();
        miLista.add("2");
        miLista.add("3");
        miLista.add("4");
        miLista.add("5");
        miLista.add("10");
         
        return miLista;
    }
    
    public Cliente getCliente(){
    
        return clienteRetorno;
    }
    
    public Cliente validarCliente(){
    
    Cliente clientePrueba = clienteRetorno;
    
    return clientePrueba;
    }
    
    public Cliente validarClienteIncorrecto(){
    
    Cliente clientePruebaIncorrecto = new Cliente();
    
    return clientePruebaIncorrecto;
    }
    
}

