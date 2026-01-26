package py.bootcamp.editorial.colegio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/colegios")
public class ColegioController {

    private final ColegioServiceImp service;

    public ColegioController(ColegioServiceImp service) {this.service = service;}

    // Listar todos los colegios
    // GET /api/colegios
    @GetMapping
    public ApiResponse<List<ColegioDto.ColegioResponse>> listar() {
        return ApiResponse.ok("Listado de Colegios", service.listar());
    }

    // Crear un Colegio
    // POST /api/colegios
    @PostMapping
    public ResponseEntity<ApiResponse<ColegioDto.ColegioResponse>> crear(@RequestBody ColegioDto.ColegioRequest req) {
        Colegio creado = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Colegio creado correctamente", service.toResponse(creado)));
    }

    // Buscar un prestamo por su ID
    // GET /api/colegios/{id}
    @GetMapping("/{id}")
    public ApiResponse<ColegioDto.ColegioResponse> obtenerPorId(@PathVariable Integer id) {
        Colegio c = service.obtenerPorId(id);
        return ApiResponse.ok("Colegio encontrado", service.toResponse(c)); }

    // Editar préstamo (cambiar profesor)
    // PUT /api/colegios/{id}
    @PutMapping("/{id}")
    public ApiResponse<ColegioDto.ColegioResponse> editar(@PathVariable Integer id, @RequestBody ColegioDto.ColegioRequest req) {
        Colegio editado = service.editar(id, req.nombre());
        return ApiResponse.ok("Colegio modificado correctamente", service.toResponse(editado));
    }

    // Borrar un prestamo
    // DELETE /api/colegios/{id}
    @DeleteMapping("/{id}")
    public ApiResponse<Void> borrar(@PathVariable Integer id) {
        service.borrar(id);
        return ApiResponse.ok("Colegio borrado correctamente", null);
    }
}
