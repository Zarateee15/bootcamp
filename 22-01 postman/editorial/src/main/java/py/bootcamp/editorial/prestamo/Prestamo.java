package py.bootcamp.editorial.prestamo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import py.bootcamp.editorial.asignatura.Asignatura;
import py.bootcamp.editorial.aula.Aula;
import py.bootcamp.editorial.colegio.Colegio;
import py.bootcamp.editorial.curso.Curso;
import py.bootcamp.editorial.detalleprestamo.DetallePrestamo;
import py.bootcamp.editorial.profesor.Profesor;

@Getter
@Setter

@Entity
@Table(name = "\"Prestamo\"")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idPrestamo\"", nullable = false)
    private Integer id;

    @Column(name = "\"fechaPrestamo\"", nullable = false)
    private LocalDate fechaPrestamo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idProfesor\"", nullable = false)
    private Profesor idProfesor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idColegio\"", nullable = false)
    private Colegio idColegio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idAsignatura\"", nullable = false)
    private Asignatura idAsignatura;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idAula\"", nullable = false)
    private Aula idAula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idCurso\"", nullable = false)
    private Curso idCurso;

    @OneToMany(mappedBy = "idPrestamo", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DetallePrestamo> detalles;


    @Override
    public String toString() {
        return id + " - " + fechaPrestamo + " - " + idProfesor;
    }

}