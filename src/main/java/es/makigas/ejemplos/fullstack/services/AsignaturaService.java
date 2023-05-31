package es.makigas.ejemplos.fullstack.services;

import es.makigas.ejemplos.fullstack.models.Asignatura;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AsignaturaService extends DatabaseService<Asignatura> {

    public AsignaturaService() {
        super(null, Asignatura.class, "Asignatura");
    }
    
    @Inject
    public AsignaturaService(PersistenceService service) {
        super(service, Asignatura.class, "Asignatura");
    }
}
