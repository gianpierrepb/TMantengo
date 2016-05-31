package pe.edu.upc.dao;

import pe.edu.upc.dao.base.EntityDao;
import pe.edu.upc.model.Cliente;

public interface TMantengoClienteDao extends EntityDao<Cliente, Integer> {
    Cliente obtenerClientePorDni(String dni) throws Exception;
}