package es.makigas.ejemplos.fullstack.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "matriculas")
@Data
public class Matricula {
    
    @Embeddable
    @Data
    public static class Key {
        @OneToOne
        @JoinColumn(name = "alumno")
        private Alumno alumno;
        
        @OneToOne
        @JoinColumn(name = "asignatura")
        private Asignatura asignatura;
        
        @Column(name = "fecha")
        private int fecha;
    }
    
    public Matricula() {
        this.key = new Key();
    }
    
    @EmbeddedId
    private Key key;
    
    @Column
    private int nota;
}
