package py.bootcamp.editorial.prestamo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PrestamoDto {
    public record PrestamoRequest (
            Integer idProfesor,
            Integer idColegio,
            Integer idAsignatura,
            Integer idAula,
            Integer idCurso,
            List<PrestamoDetalleRequest> items
    ) { }

    public record PrestamoDetalleRequest(
            Integer idLibro,
            Integer cantidad
    ){}

    public record EditarPrestamoRequest(
            Integer idProfesor,
            Integer idColegio,
            Integer idAsignatura,
            Integer idAula,
            Integer idCurso
    ) {}

    // JSON a mostrar
    public record PrestamoDetalleResponse(
            String Libro,
            Integer Cantidad
    ) {}

    public record PrestamoResponse(
            LocalDate FechaPrestamo,
            String Profesor,
            BigDecimal Cedula,
            String Colegio,
            String Asignatura,
            String Aula,
            String Curso,
            List<PrestamoDetalleResponse> Detalles
    ) {}
}