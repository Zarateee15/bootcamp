package py.bootcamp.editorial.usuario;

import java.util.List;

public interface UsuarioService {
    Usuario crear(UsuarioDto.UsuarioRequest request);
    List<UsuarioDto.UsuarioResponse> listar();
    Usuario obtenerPorId(Integer id);
    Usuario editar (Integer id, String username);
    void borrar (Integer id);
    UsuarioDto.UsuarioResponse toResponse (Usuario c);
}
