package py.bootcamp.editorial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import py.bootcamp.editorial.colegio.Colegio;

@Getter
@Setter

@Entity
@Table(name = "\"AsignacionProfesor\"")
public class AsignacionProfesor {
    @EmbeddedId
    private AsignacionProfesorId id;

    @MapsId("idColegio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idColegio\"", nullable = false)
    private Colegio idColegio;

    @MapsId("idAsignatura")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idAsignatura\"", nullable = false)
    private Asignatura idAsignatura;

    @MapsId("idAula")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idAula\"", nullable = false)
    private Aula idAula;

    @MapsId("idCurso")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idCurso\"", nullable = false)
    private Curso idCurso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"idProfesor\"", nullable = false)
    private Profesor idProfesor;

}