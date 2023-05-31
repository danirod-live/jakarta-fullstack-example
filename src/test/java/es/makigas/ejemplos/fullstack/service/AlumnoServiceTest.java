package es.makigas.ejemplos.fullstack.service;

import es.makigas.ejemplos.fullstack.models.Alumno;
import es.makigas.ejemplos.fullstack.services.AlumnoService;
import es.makigas.ejemplos.fullstack.services.PersistenceService;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public class AlumnoServiceTest {

    public static void main(String[] args) {
        PersistenceService service = new PersistenceService();
        AlumnoService alumnos = new AlumnoService(service);
        
        // List. Should print first records.
        System.out.println("== PAGE 1 ==");
        List<Alumno> query = alumnos.list().page(1).limit(2).get();
        query.forEach(System.out::println);
        System.out.println("== PAGE 2 ==");
        query = alumnos.list().page(2).limit(2).get();
        query.forEach(System.out::println);
        
        // Insert a new record.
        System.out.println("== TEST INSERT ==");
        Alumno alu = new Alumno();
        alu.setNombre("Liken");
        alu.setApellidos("Dero");
        alu.setFechaNacimiento(LocalDate.of(2004, Month.SEPTEMBER, 30));
        System.out.println("null -> " + alu.getId());
        alumnos.insert(alu);
        System.out.println("not null -> " + alu.getId());
        
        // Get all records.
        System.out.println("== TEST LIST ALL ==");
        alumnos.list().page(1).limit(200).get().forEach(System.out::println);
        
        // GET 1
        System.out.println("== GET 1 ==");
        Optional<Alumno> copy = alumnos.get(alu.getId());
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        copy.ifPresent(System.out::println);
        
        // Modify
        System.out.println("== TEST MODIFY ==");
        alu.setNombre("Laken");
        alumnos.update(alu);
        
        // GET 2
        System.out.println("== GET 2 ==");
        copy = alumnos.get(alu.getId());
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        copy.ifPresent(System.out::println);
        
        // GET NULL
        System.out.println("== GET NULL ==");
        copy = alumnos.get(-1);
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        
        // DELETE
        System.out.println("== DELETE ==");
        alumnos.delete(alu);
        
        // Get all records.
        System.out.println("== TEST LIST ALL ==");
        alumnos.list().page(1).limit(200).get().forEach(System.out::println);
    }
    
}
