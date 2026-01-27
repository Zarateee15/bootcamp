package py.bootcamp.editorial.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    public UsuarioServiceImp(UsuarioRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public List<UsuarioDto.UsuarioResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public Usuario crear(UsuarioDto.UsuarioRequest request) {
        if (request.username() == null || request.username().isBlank()
                || request.password() == null || request.password().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username y Password son obligatorios");
        }

        if (repo.findByUsername(request.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El username ya existe");
        }

        Usuario u = new Usuario();
        u.setUsername(request.username());
        u.setPassword(encoder.encode(request.password()));

        return repo.save(u);
    }

    public Usuario obtenerPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no encontrado: " + id
                ));
    }

    public Usuario editar (Integer id, String username) {
        Usuario a = obtenerPorId(id);
        a.setUsername(username);
        return repo.save(a);
    }

    public void borrar (Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado: " + id);
        }
        repo.deleteById(id);
    }

    // Helper para formatear el JSON
    public UsuarioDto.UsuarioResponse toResponse(Usuario c) {
        return new UsuarioDto.UsuarioResponse(
                c.getIdUsuario(),
                c.getUsername()
        );
    }
}
