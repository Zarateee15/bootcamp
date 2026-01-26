package py.bootcamp.editorial.aula;

public class AulaDto {
    public record AulaRequest (
            String nombre
    ) { }

    // JSON a mostrar
    public record AulaResponse(
            Integer idAula,
            String nombre
    ) {}
}
