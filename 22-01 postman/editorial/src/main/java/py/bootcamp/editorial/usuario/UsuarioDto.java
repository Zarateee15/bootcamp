package py.bootcamp.editorial.usuario;

public class UsuarioDto {

    public record UsuarioRequest (
            String username,
            String password
    ) { }

    // JSON a mostrar
    public record UsuarioResponse(
            Integer idUsuario,
            String username
    ) {}

}
