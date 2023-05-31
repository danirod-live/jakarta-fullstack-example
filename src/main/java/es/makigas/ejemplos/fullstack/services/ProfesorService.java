package es.makigas.ejemplos.fullstack.services;

import es.makigas.ejemplos.fullstack.models.Profesor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ProfesorService extends DatabaseService<Profesor> {

    public ProfesorService() {
        super(null, Profesor.class, "Profesor");
    }

    @Inject
    public ProfesorService(PersistenceService service) {
        super(service, Profesor.class, "Profesor");
    }
}
