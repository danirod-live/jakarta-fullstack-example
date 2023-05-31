package es.makigas.ejemplos.fullstack.services;

import es.makigas.ejemplos.fullstack.models.Alumno;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AlumnoService extends DatabaseService<Alumno> {
    
    public AlumnoService() {
        super(null, Alumno.class, "Alumno");
        System.out.println("AlumnoService()");
    }
    
    @Inject
    public AlumnoService(PersistenceService service) {
        super(service, Alumno.class, "Alumno");
        System.out.println("AlumnoService() con cosas");
    }

}
