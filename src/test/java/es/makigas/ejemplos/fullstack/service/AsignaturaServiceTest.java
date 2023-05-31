package es.makigas.ejemplos.fullstack.service;

import es.makigas.ejemplos.fullstack.models.Asignatura;
import es.makigas.ejemplos.fullstack.models.Profesor;
import es.makigas.ejemplos.fullstack.services.PersistenceService;
import es.makigas.ejemplos.fullstack.services.AsignaturaService;
import es.makigas.ejemplos.fullstack.services.ProfesorService;
import java.util.List;
import java.util.Optional;

public class AsignaturaServiceTest {

    public static void main(String[] args) {
        PersistenceService service = new PersistenceService();
        ProfesorService profes = new ProfesorService(service);
        AsignaturaService asigs = new AsignaturaService(service);
        
        // List. Should print first records.
        System.out.println("== PAGE 1 ==");
        List<Asignatura> query = asigs.list().page(1).limit(2).get();
        query.forEach(System.out::println);
        System.out.println("== PAGE 2 ==");
        query = asigs.list().page(2).limit(2).get();
        query.forEach(System.out::println);
        
        // Insert a new record.
        Profesor p = profes.get(1).get();
        System.out.println("== TEST INSERT ==");
        Asignatura asi = new Asignatura();
        asi.setNombre("Compiladores");
        asi.setProfesor(p);
        System.out.println("null -> " + asi.getId());
        asigs.insert(asi);
        System.out.println("not null -> " + asi.getId());
        
        // Get all records.
        System.out.println("== TEST LIST ALL ==");
        asigs.list().page(1).limit(200).get().forEach(System.out::println);
        
        // GET 1
        System.out.println("== GET 1 ==");
        Optional<Asignatura> copy = asigs.get(asi.getId());
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        copy.ifPresent(System.out::println);
        
        // Modify
        System.out.println("== TEST MODIFY ==");
        asi.setNombre("Compiladores y analizadores");
        asigs.update(asi);
        
        // GET 2
        System.out.println("== GET 2 ==");
        copy = asigs.get(asi.getId());
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        copy.ifPresent(System.out::println);
        
        // GET NULL
        System.out.println("== GET NULL ==");
        copy = asigs.get(-1);
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        
        // DELETE
        System.out.println("== DELETE ==");
        asigs.delete(asi);
        
        // Get all records.
        System.out.println("== TEST LIST ALL ==");
        asigs.list().page(1).limit(200).get().forEach(System.out::println);
    }
    
}
