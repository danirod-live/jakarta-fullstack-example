package es.makigas.ejemplos.fullstack.service;

import es.makigas.ejemplos.fullstack.models.Profesor;
import es.makigas.ejemplos.fullstack.services.PersistenceService;
import es.makigas.ejemplos.fullstack.services.ProfesorService;
import java.util.List;
import java.util.Optional;

public class ProfesorServiceTest {

    public static void main(String[] args) {
        PersistenceService service = new PersistenceService();
        ProfesorService profes = new ProfesorService(service);
        
        // List. Should print first records.
        System.out.println("== PAGE 1 ==");
        List<Profesor> query = profes.list().page(1).limit(2).get();
        query.forEach(System.out::println);
        System.out.println("== PAGE 2 ==");
        query = profes.list().page(2).limit(2).get();
        query.forEach(System.out::println);
        
        // Insert a new record.
        System.out.println("== TEST INSERT ==");
        Profesor pro = new Profesor();
        pro.setNombre("Jon");
        pro.setApellidos("Grizzly");
        System.out.println("null -> " + pro.getId());
        profes.insert(pro);
        System.out.println("not null -> " + pro.getId());
        
        // Get all records.
        System.out.println("== TEST LIST ALL ==");
        profes.list().page(1).limit(200).get().forEach(System.out::println);
        
        // GET 1
        System.out.println("== GET 1 ==");
        Optional<Profesor> copy = profes.get(pro.getId());
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        copy.ifPresent(System.out::println);
        
        // Modify
        System.out.println("== TEST MODIFY ==");
        pro.setApellidos("Polar");
        profes.update(pro);
        
        // GET 2
        System.out.println("== GET 2 ==");
        copy = profes.get(pro.getId());
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        copy.ifPresent(System.out::println);
        
        // GET NULL
        System.out.println("== GET NULL ==");
        copy = profes.get(-1);
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        
        // DELETE
        System.out.println("== DELETE ==");
        profes.delete(pro);
        
        // Get all records.
        System.out.println("== TEST LIST ALL ==");
        profes.list().page(1).limit(200).get().forEach(System.out::println);
    }
    
}
