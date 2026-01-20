package py.bootcamp.editorial.prestamolibro;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import py.bootcamp.editorial.detalleprestamo.DetallePrestamo;
import py.bootcamp.editorial.libro.Libro;

@Getter
@Setter

@Entity
@Table(name = "\"PrestamoLibro\"")
public class PrestamoLibro {
    @EmbeddedId
    private PrestamoLibroId id;

    @MapsId("idDetalle")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idDetalle\"", nullable = false)
    private DetallePrestamo idDetalle;

    @MapsId("idLibro")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idLibro\"", nullable = false)
    private Libro idLibro;

}