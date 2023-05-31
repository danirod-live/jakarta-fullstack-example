package es.makigas.ejemplos.fullstack.services;

import es.makigas.ejemplos.fullstack.dao.CRUD;
import es.makigas.ejemplos.fullstack.dao.Query;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

class DatabaseService<M> implements CRUD<M> {

    private PersistenceService persistence;
    
    private Class<M> targetClass;
    
    private String entityName;
    
    public DatabaseService(PersistenceService persistence, Class<M> target, String entityName) {
        this.persistence = persistence;
        this.targetClass = target;
        this.entityName = entityName;
    }
    
    class QueryBuilder implements Query<M> {
        
        private int page = 1;
        
        private int limit = 10;

        @Override
        public Query<M> page(int pag) {
            this.page = pag;
            return this;
        }

        @Override
        public Query<M> limit(int limit) {
            this.limit = limit;
            return this;
        }

        @Override
        public List<M> get() {
            EntityManager em = persistence.getEntityManager();
            String query = "SELECT e FROM {e} e".replace("{e}", entityName);
            int offset = (page - 1) * limit;
            return (List<M>) em.createQuery(query)
                    .setMaxResults(limit)
                    .setFirstResult(offset)
                    .getResultList();
            
        }
        
    }
    
    @Override
    public Query<M> list() {
        return new QueryBuilder();
    }

    @Override
    public Optional<M> get(Object id) {
        M entity = this.persistence.getEntityManager().find(this.targetClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public M insert(M model) { 
        this.persistence.getEntityManager().getTransaction().begin();
        this.persistence.getEntityManager().persist(model);
        this.persistence.getEntityManager().getTransaction().commit();
        return model;
    }

    @Override
    public void update(M model) {
        this.persistence.getEntityManager().getTransaction().begin();
        this.persistence.getEntityManager().merge(model);
        this.persistence.getEntityManager().getTransaction().commit();
    }

    @Override
    public void delete(M model) {
        this.persistence.getEntityManager().getTransaction().begin();
        this.persistence.getEntityManager().remove(model);
        this.persistence.getEntityManager().getTransaction().commit();
    }
    
}
