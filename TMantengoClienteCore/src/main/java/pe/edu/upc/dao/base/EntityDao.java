package pe.edu.upc.dao.base;

import java.util.List;

public interface EntityDao<E, J> {
    
    void insertar(E entity) throws Exception;
    
    void actualizar(E entity) throws Exception;
    
    void eliminar(J entityId) throws Exception;
    
    E obtener(J entityId) throws Exception;
    
    List<E> listar() throws Exception;
}
