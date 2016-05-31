package pe.edu.upc.dao.impl;

import java.util.List;
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
}

