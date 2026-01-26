package py.bootcamp.editorial.profesor;

import java.math.BigDecimal;

public class ProfesorDto {
    public record ProfesorRequest (
            String nombre,
            BigDecimal cedula
    ) { }

    // JSON a mostrar
    public record ProfesorResponse(
            Integer idProfesor,
            String nombre,
            BigDecimal cedula

    ) {}
}