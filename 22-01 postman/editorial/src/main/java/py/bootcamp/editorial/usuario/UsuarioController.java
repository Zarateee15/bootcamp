package py.bootcamp.editorial.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServiceImp service;

    public UsuarioController(UsuarioServiceImp service) {
        this.service = service;
    }

    // Listar todos los usuarios
    // GET /api/usuarios
    @GetMapping
    public ApiResponse<List<UsuarioDto.UsuarioResponse>> listar() {
        return ApiResponse.ok("Listado de Usuarios", service.listar());
    }

    // Crear un Usuario
    // POST /api/usuarios
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioDto.UsuarioResponse>> crear(@RequestBody UsuarioDto.UsuarioRequest req) {
        Usuario guardado = service.crear(req);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Usuario creado correctamente", service.toResponse(guardado)));
    }

    // Buscar un usuario por su ID
    // GET /api/usuarios/{id}
    @GetMapping("/{id}")
    public ApiResponse<UsuarioDto.UsuarioResponse> obtenerPorId(@PathVariable Integer id) {
        Usuario c = service.obtenerPorId(id);
        return ApiResponse.ok("Usuario encontrado", service.toResponse(c)); }

    // Editar usuario (cambiar profesor)
    // PUT /api/usuarios/{id}
    @PutMapping("/{id}")
    public ApiResponse<UsuarioDto.UsuarioResponse> editar(@PathVariable Integer id, @RequestBody UsuarioDto.UsuarioRequest req) {
        Usuario editado = service.editar(id, req.username());
        return ApiResponse.ok("Usuario modificado correctamente", service.toResponse(editado));
    }

    // Borrar un usuario
    // DELETE /api/usuarios/{id}
    @DeleteMapping("/{id}")
    public ApiResponse<Void> borrar(@PathVariable Integer id) {
        service.borrar(id);
        return ApiResponse.ok("Usuario borrado correctamente", null);
    }
}
