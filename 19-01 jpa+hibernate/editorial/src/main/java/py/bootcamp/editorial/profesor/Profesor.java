package py.bootcamp.editorial.profesor;

import jakarta.persistence.*;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "\"Profesor\"")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idProfesor\"", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "cedula", nullable = false, precision = 7)
    private BigDecimal cedula;

    @Override
    public String toString() {
        return id + " - " + nombre + " - " + cedula;
    }

}