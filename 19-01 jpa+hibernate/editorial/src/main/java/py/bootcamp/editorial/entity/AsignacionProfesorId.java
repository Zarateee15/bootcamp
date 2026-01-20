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
public class AsignacionProfesorId implements Serializable {
    private static final long serialVersionUID = -4515466362792589874L;
    @Column(name = "\"idColegio\"", nullable = false)
    private Integer idColegio;

    @Column(name = "\"idAsignatura\"", nullable = false)
    private Integer idAsignatura;

    @Column(name = "\"idAula\"", nullable = false)
    private Integer idAula;

    @Column(name = "\"idCurso\"", nullable = false)
    private Integer idCurso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignacionProfesorId entity = (AsignacionProfesorId) o;
        return Objects.equals(this.idColegio, entity.idColegio) &&
                Objects.equals(this.idAsignatura, entity.idAsignatura) &&
                Objects.equals(this.idAula, entity.idAula) &&
                Objects.equals(this.idCurso, entity.idCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idColegio, idAsignatura, idAula, idCurso);
    }
}