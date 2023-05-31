package es.makigas.ejemplos.fullstack.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class PersistenceService {
    
    private EntityManager manager;
    
    public EntityManager getEntityManager() {
        if (manager == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("application");
            manager = emf.createEntityManager();
        }
        return manager;
    }
    
}
