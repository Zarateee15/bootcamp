package py.bootcamp.editorial.prestamo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
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

    @OneToMany(mappedBy = "idPrestamo", fetch = FetchType.LAZY)
    private List<DetallePrestamo> detalles;


    @Override
    public String toString() {
        return id + " - " + fechaPrestamo + " - " + idProfesor;
    }

}