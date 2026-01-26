package py.bootcamp.editorial.aula;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    private final AulaServiceImp service;

    public AulaController(AulaServiceImp service) {this.service = service;}

    // Listar todos las aulas
    // GET /api/aulas
    @GetMapping
    public ApiResponse<List<AulaDto.AulaResponse>> listar() {
        return ApiResponse.ok("Listado de Aulas", service.listar());
    }

    // Crear un Aula
    // POST /api/aulas
    @PostMapping
    public ResponseEntity<ApiResponse<AulaDto.AulaResponse>> crear(@RequestBody AulaDto.AulaRequest req) {
        Aula creado = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Aula creada correctamente", service.toResponse(creado)));
    }

    // Buscar un aula por su ID
    // GET /api/aulas/{id}
    @GetMapping("/{id}")
    public ApiResponse<AulaDto.AulaResponse> obtenerPorId(@PathVariable Integer id) {
        Aula c = service.obtenerPorId(id);
        return ApiResponse.ok("Aula encontrada", service.toResponse(c)); }

    // Editar aula (cambiar nombre)
    // PUT /api/aulas/{id}
    @PutMapping("/{id}")
    public ApiResponse<AulaDto.AulaResponse> editar(@PathVariable Integer id, @RequestBody AulaDto.AulaRequest req) {
        Aula editado = service.editar(id, req.nombre());
        return ApiResponse.ok("Aula modificada correctamente", service.toResponse(editado));
    }

    // Borrar un aula
    // DELETE /api/aulas/{id}
    @DeleteMapping("/{id}")
    public ApiResponse<Void> borrar(@PathVariable Integer id) {
        service.borrar(id);
        return ApiResponse.ok("Aula borrada correctamente", null);
    }
}
