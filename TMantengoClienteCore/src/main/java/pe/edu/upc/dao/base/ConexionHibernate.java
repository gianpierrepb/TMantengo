package pe.edu.upc.dao.base;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConexionHibernate {
    
    private static SessionFactory sessionFactory;
    
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex){
            throw new HibernateException(ex);
        }
    }    
    
    protected Session obtenerSesionActual(){
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
    
    protected Session obtenerSesion(){
        Session session = sessionFactory.openSession();
        return session;
    }
    
    protected void cerrarSesion(Session session){
        try {
            if (session != null){
                session.close();
            } 
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
