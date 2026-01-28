package py.bootcamp.editorial.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import py.bootcamp.editorial.rol.Rol;

@Getter
@Setter
@Entity
@Table(name = "\"Usuario\"")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"idUsuario\"", nullable = false)
    private Integer idUsuario;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "\"idRol\"", nullable = false)
    private Rol rol;

}
