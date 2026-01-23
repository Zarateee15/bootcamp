package py.bootcamp.editorial.libro;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import py.bootcamp.editorial.editorial.Editorial;

@Getter
@Setter

@Entity
@Table(name = "\"Libro\"")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idLibro\"", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "\"cantidadCopias\"", nullable = false)
    private Integer cantidadCopias;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idEditorial\"", nullable = false)
    private Editorial idEditorial;

    @Override
    public String toString() {
        return id + " - " + nombre + " - " + cantidadCopias;
    }

}