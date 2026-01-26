package py.bootcamp.editorial.libro;
import py.bootcamp.editorial.editorial.EditorialDto;


public class LibroDto {
    public record LibroRequest (
            String nombre,
            Integer cantidadCopias,
            Integer idEditorial
    ) { }

    // JSON a mostrar
    public record LibroResponse(
            Integer idLibro,
            String nombre,
            Integer cantidadCopias,
            EditorialDto.EditorialResponse editorial
    ) {}
}