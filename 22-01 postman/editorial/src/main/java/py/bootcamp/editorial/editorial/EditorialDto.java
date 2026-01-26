package py.bootcamp.editorial.editorial;

public class EditorialDto {
    public record EditorialRequest (
            String nombre
    ) { }

    // JSON a mostrar
    public record EditorialResponse(
            Integer idEditorial,
            String nombre
    ) {}
}