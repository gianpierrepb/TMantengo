package pe.edu.upc.service.impl;

import java.util.List;
import pe.edu.upc.dao.TMantengoClienteDao;
import pe.edu.upc.dao.impl.TMantengoClienteHibernateDao;
import pe.edu.upc.model.Cliente;
import pe.edu.upc.service.TMantengoClienteService;

public class TMantengoClienteServiceImpl implements TMantengoClienteService {

    private static final TMantengoClienteServiceImpl CLIENTE_SERVICE_IMPL;
    private TMantengoClienteDao clienteDao = null;

    static {
        CLIENTE_SERVICE_IMPL = new TMantengoClienteServiceImpl();
    }

    private TMantengoClienteServiceImpl() {
        clienteDao = (TMantengoClienteDao) TMantengoClienteHibernateDao.getInstance();
    }

    public static TMantengoClienteServiceImpl getInstance() {
        return CLIENTE_SERVICE_IMPL;
    }

    @Override
    public void insertar(Cliente entity) throws Exception {
        clienteDao.insertar(entity);
    }

    @Override
    public void actualizar(Cliente entity) throws Exception {
        clienteDao.actualizar(entity);
    }

    @Override
    public void eliminar(Integer entityId) throws Exception {
        clienteDao.eliminar(entityId);
    }

    @Override
    public Cliente obtener(Integer entityId) throws Exception {
        return clienteDao.obtener(entityId);
    }

    @Override
    public List<Cliente> listar() throws Exception {
        return clienteDao.listar();//cambiado listar - Listar
    }

    @Override
    public Cliente obtenerClientePorDni(String dni) throws Exception {
        return clienteDao.obtenerClientePorDni(dni);
    }
    
}
