package py.bootcamp.editorial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class PrestamoLibroId implements Serializable {
    private static final long serialVersionUID = -5892651235613648091L;
    @Column(name = "\"idDetalle\"", nullable = false)
    private Integer idDetalle;

    @Column(name = "\"idLibro\"", nullable = false)
    private Integer idLibro;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrestamoLibroId entity = (PrestamoLibroId) o;
        return Objects.equals(this.idDetalle, entity.idDetalle) &&
                Objects.equals(this.idLibro, entity.idLibro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalle, idLibro);
    }
}