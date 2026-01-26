package py.bootcamp.editorial.curso;

public class CursoDto {
    public record CursoRequest (
            String nombre
    ) { }

    // JSON a mostrar
    public record CursoResponse(
            Integer idCurso,
            String nombre
    ) {}
}
