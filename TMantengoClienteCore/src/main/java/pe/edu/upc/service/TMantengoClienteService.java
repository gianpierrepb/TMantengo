package pe.edu.upc.service;

import pe.edu.upc.model.Cliente;
import pe.edu.upc.service.base.BaseService;

public interface TMantengoClienteService extends BaseService<Cliente, Integer> {
    Cliente obtenerClientePorDni(String dni) throws Exception;
}
