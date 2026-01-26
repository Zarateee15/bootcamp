package py.bootcamp.editorial.colegio;

public class ColegioDto {
    public record ColegioRequest (
            String nombre
    ) { }

    // JSON a mostrar
    public record ColegioResponse(
            Integer idColegio,
            String nombre
    ) {}
}