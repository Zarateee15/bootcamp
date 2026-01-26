package py.bootcamp.editorial.editorial;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

    private final EditorialServiceImp service;

    public EditorialController(EditorialServiceImp service) {this.service = service;}

    // Listar todos los editoriales
    // GET /api/editoriales
    @GetMapping
    public ApiResponse<List<EditorialDto.EditorialResponse>> listar() {
        return ApiResponse.ok("Listado de Editoriales", service.listar());
    }

    // Crear una Editorial
    // POST /api/editoriales
    @PostMapping
    public ResponseEntity<ApiResponse<EditorialDto.EditorialResponse>> crear(@RequestBody EditorialDto.EditorialRequest req) {
        Editorial creado = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Editorial creada correctamente", service.toResponse(creado)));
    }

    // Buscar una Editorial por su ID
    // GET /api/editoriales/{id}
    @GetMapping("/{id}")
    public ApiResponse<EditorialDto.EditorialResponse> obtenerPorId(@PathVariable Integer id) {
        Editorial c = service.obtenerPorId(id);
        return ApiResponse.ok("Editorial encontrada", service.toResponse(c)); }

    // Editar Editorial (cambiar nombre)
    // PUT /api/editoriales/{id}
    @PutMapping("/{id}")
    public ApiResponse<EditorialDto.EditorialResponse> editar(@PathVariable Integer id, @RequestBody EditorialDto.EditorialRequest req) {
        Editorial editado = service.editar(id, req.nombre());
        return ApiResponse.ok("Editorial modificada correctamente", service.toResponse(editado));
    }

    // Borrar una Editorial
    // DELETE /api/editoriales/{id}
    @DeleteMapping("/{id}")
    public ApiResponse<Void> borrar(@PathVariable Integer id) {
        service.borrar(id);
        return ApiResponse.ok("Editorial borrada correctamente", null);
    }
}
