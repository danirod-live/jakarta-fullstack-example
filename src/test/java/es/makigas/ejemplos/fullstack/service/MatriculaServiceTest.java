package es.makigas.ejemplos.fullstack.service;

import es.makigas.ejemplos.fullstack.models.Alumno;
import es.makigas.ejemplos.fullstack.models.Asignatura;
import es.makigas.ejemplos.fullstack.models.Matricula;
import es.makigas.ejemplos.fullstack.services.AlumnoService;
import es.makigas.ejemplos.fullstack.services.AsignaturaService;
import es.makigas.ejemplos.fullstack.services.MatriculaService;
import es.makigas.ejemplos.fullstack.services.PersistenceService;
import java.util.List;
import java.util.Optional;

public class MatriculaServiceTest {

    public static void main(String[] args) {
        PersistenceService service = new PersistenceService();
        AsignaturaService asigs = new AsignaturaService(service);
        AlumnoService alus = new AlumnoService(service);
        MatriculaService matris = new MatriculaService(service);
        
        // List. Should print first records.
        System.out.println("== PAGE 1 ==");
        List<Matricula> query = matris.list().page(1).limit(2).get();
        query.forEach(System.out::println);
        System.out.println("== PAGE 2 ==");
        query = matris.list().page(2).limit(2).get();
        query.forEach(System.out::println);

        // Insert a new record.
        System.out.println("== TEST INSERT ==");
        Alumno alu = alus.get(1).get();
        Asignatura asi = asigs.get(6).get();
        Matricula matri = new Matricula();
        matri.getKey().setAlumno(alu);
        matri.getKey().setAsignatura(asi);
        matri.getKey().setFecha(2021);
        matri.setNota(10);
        
        System.out.println("null -> " + matri.getKey());
        matris.insert(matri);
        System.out.println("not null -> " + matri.getKey());
        
        // Get all records.
        System.out.println("== TEST LIST ALL ==");
        matris.list().page(1).limit(200).get().forEach(System.out::println);
        
        // GET 1
        System.out.println("== GET 1 ==");
        Optional<Matricula> copy = matris.get(matri.getKey());
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        copy.ifPresent(System.out::println);
        
        // Modify
        System.out.println("== TEST MODIFY ==");
        matri.setNota(0);
        matris.update(matri);
        
        // GET 2
        System.out.println("== GET 2 ==");
        copy = matris.get(matri.getKey());
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        copy.ifPresent(System.out::println);
        
        // GET NULL
        System.out.println("== GET NULL ==");
        copy = matris.get(new Matricula.Key());
        System.out.println(copy.map(_whatever -> "(present)").orElse("(not present)"));
        
        // DELETE
        System.out.println("== DELETE ==");
        matris.delete(matri);
        
        // Get all records.
        System.out.println("== TEST LIST ALL ==");
        matris.list().page(1).limit(200).get().forEach(System.out::println);
    }
    
}
