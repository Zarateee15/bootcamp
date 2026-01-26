package py.bootcamp.editorial.asignatura;

public class AsignaturaDto {
    public record AsignaturaRequest (
            String nombre
    ) { }

    // JSON a mostrar
    public record AsignaturaResponse(
            Integer idAsignatura,
            String nombre
    ) {}
}