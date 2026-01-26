package py.bootcamp.editorial.detalleprestamo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import py.bootcamp.editorial.libro.Libro;
import py.bootcamp.editorial.prestamo.Prestamo;

@Getter
@Setter
@Entity
@Table(name = "\"DetallePrestamo\"")
public class DetallePrestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idDetalle\"", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "\"idPrestamo\"", nullable = false)
    private Prestamo idPrestamo;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // ponelo false si en DB es NOT NULL
    @JoinColumn(name = "\"idLibro\"", nullable = false)
    private Libro idLibro;
}