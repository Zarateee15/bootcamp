package py.bootcamp.editorial.prestamo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PrestamoDto {
    public record PrestamoRequest (
            Integer idProfesor,
            List<PrestamoDetalleRequest> items
    ) { }

    public record PrestamoDetalleRequest(
            Integer idLibro,
            Integer cantidad
    ){}

    public record EditarPrestamoRequest(
            Integer idProfesor
    ) {}

    // JSON a mostrar
    public record PrestamoDetalleResponse(
            String nombreLibro,
            Integer cantidad
    ) {}

    public record PrestamoResponse(
            LocalDate fechaPrestamo,
            String nombreProfesor,
            BigDecimal cedula,
            List<PrestamoDetalleResponse> detalles
    ) {}
}