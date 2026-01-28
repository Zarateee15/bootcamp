package py.bootcamp.editorial.rol;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "\"Rol\"")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idRol\"")
    private Integer idRol;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;
}
