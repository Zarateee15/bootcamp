package py.bootcamp.editorial.asignatura;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    private final AsignaturaService service;

    public AsignaturaController(AsignaturaService service) {
        this.service = service;
    }

    // Listar todas las asignaturas
    // GET /api/asignaturas/listar
    @GetMapping("/listar")
    public ApiResponse<List<Asignatura>> listar() {
        return ApiResponse.ok("Listado de asignaturas", service.listar());
    }

    // Crear una Asignatura
    // POST /api/asignaturas/crear
    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<Asignatura>> crear(@RequestBody AsignaturaDto.AsignaturaRequest req) {
        Asignatura creada = service.crear(req.nombre());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Asignatura creada correctamente", creada));
    }

    // Buscar una asignatura especifica por su ID
    // GET /api/asignaturas/buscar/{id}
    @GetMapping("/buscar/{id}")
    public Asignatura obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    // Editar una asignatura
    // PUT /api/asignaturas/editar/{id}
    @PutMapping("/editar/{id}")
    public ApiResponse<Asignatura> editar(@PathVariable Integer id, @RequestBody AsignaturaDto.AsignaturaRequest req) {
        Asignatura editada = service.editar(id, req.nombre());
        return ApiResponse.ok("Asignatura modificada correctamente", editada);
    }

    // Borrar una asignatura
    // DELETE /api/asignaturas/borrar/{id}
    @DeleteMapping("/borrar/{id}")
    public ApiResponse<Void> borrar (@PathVariable Integer id) {
        service.borrar(id);
        return ApiResponse.ok("Asignatura borrada correctamente", null); // 204
    }
}
