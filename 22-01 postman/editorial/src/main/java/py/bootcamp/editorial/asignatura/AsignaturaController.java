package py.bootcamp.editorial.asignatura;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    private final AsignaturaServiceImp service;

    public AsignaturaController(AsignaturaServiceImp service) {this.service = service;}

    // Listar todos los asignaturas
    // GET /api/asignaturas
    @GetMapping
    public ApiResponse<List<AsignaturaDto.AsignaturaResponse>> listar() {
        return ApiResponse.ok("Listado de Asignaturas", service.listar());
    }

    // Crear una Asignatura
    // POST /api/asignaturas
    @PostMapping
    public ResponseEntity<ApiResponse<AsignaturaDto.AsignaturaResponse>> crear(@RequestBody AsignaturaDto.AsignaturaRequest req) {
        Asignatura creado = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Asignatura creado correctamente", service.toResponse(creado)));
    }

    // Buscar una asignatura por su ID
    // GET /api/asignaturas/{id}
    @GetMapping("/{id}")
    public ApiResponse<AsignaturaDto.AsignaturaResponse> obtenerPorId(@PathVariable Integer id) {
        Asignatura c = service.obtenerPorId(id);
        return ApiResponse.ok("Asignatura encontrado", service.toResponse(c)); }

    // Editar asignatura (cambiar nombre)
    // PUT /api/asignaturas/{id}
    @PutMapping("/{id}")
    public ApiResponse<AsignaturaDto.AsignaturaResponse> editar(@PathVariable Integer id, @RequestBody AsignaturaDto.AsignaturaRequest req) {
        Asignatura editado = service.editar(id, req.nombre());
        return ApiResponse.ok("Asignatura modificado correctamente", service.toResponse(editado));
    }

    // Borrar un asignatura
    // DELETE /api/asignaturas/{id}
    @DeleteMapping("/{id}")
    public ApiResponse<AsignaturaDto.AsignaturaResponse> borrar(@PathVariable Integer id) {
        Asignatura borrada = service.obtenerPorId(id);
        service.borrar(id);
        return ApiResponse.ok("Asignatura borrado correctamente", service.toResponse(borrada));
    }
}
