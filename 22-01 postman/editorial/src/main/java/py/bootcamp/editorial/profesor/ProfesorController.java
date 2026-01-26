package py.bootcamp.editorial.profesor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorServiceImp service;

    public ProfesorController(ProfesorServiceImp service) {this.service = service;}

    // Listar todos los profesores
    // GET /api/profesores
    @GetMapping
    public ApiResponse<List<ProfesorDto.ProfesorResponse>> listar() {
        return ApiResponse.ok("Listado de Profesores", service.listar());
    }

    // Crear un Profesor
    // POST /api/profesores
    @PostMapping
    public ResponseEntity<ApiResponse<ProfesorDto.ProfesorResponse>> crear(@RequestBody ProfesorDto.ProfesorRequest req) {
        Profesor creado = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Profesor creado correctamente", service.toResponse(creado)));
    }

    // Buscar un profesor por su ID
    // GET /api/profesores/{id}
    @GetMapping("/{id}")
    public ApiResponse<ProfesorDto.ProfesorResponse> obtenerPorId(@PathVariable Integer id) {
        Profesor c = service.obtenerPorId(id);
        return ApiResponse.ok("Profesor encontrado", service.toResponse(c)); }

    // Editar profesor (cambiar nombre y cedula)
    // PUT /api/profesores/{id}
    @PutMapping("/{id}")
    public ApiResponse<ProfesorDto.ProfesorResponse> editar(@PathVariable Integer id, @RequestBody ProfesorDto.ProfesorRequest req) {
        Profesor editado = service.editar(id, req.nombre(), req.cedula());
        return ApiResponse.ok("Profesor modificado correctamente", service.toResponse(editado));
    }

    // Borrar un profesor
    // DELETE /api/profesores/{id}
    @DeleteMapping("/{id}")
    public ApiResponse<Void> borrar(@PathVariable Integer id) {
        service.borrar(id);
        return ApiResponse.ok("Profesor borrado correctamente", null);
    }
}
