package es.makigas.ejemplos.fullstack.dao;

import java.util.Optional;

public interface CRUD<M>
{    
    Query<M> list();
    
    Optional<M> get(Object id);
    
    M insert(M model);
    
    void update(M model);
    
    void delete(M model);
}
