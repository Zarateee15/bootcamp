package py.bootcamp.editorial.curso;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoServiceImp service;

    public CursoController(CursoServiceImp service) {this.service = service;}

    // Listar todos los cursos
    // GET /api/cursos
    @GetMapping
    public ApiResponse<List<CursoDto.CursoResponse>> listar() {
        return ApiResponse.ok("Listado de Cursos", service.listar());
    }

    // Crear un Curso
    // POST /api/cursos
    @PostMapping
    public ResponseEntity<ApiResponse<CursoDto.CursoResponse>> crear(@RequestBody CursoDto.CursoRequest req) {
        Curso creado = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Curso creado correctamente", service.toResponse(creado)));
    }

    // Buscar un curso por su ID
    // GET /api/cursos/{id}
    @GetMapping("/{id}")
    public ApiResponse<CursoDto.CursoResponse> obtenerPorId(@PathVariable Integer id) {
        Curso c = service.obtenerPorId(id);
        return ApiResponse.ok("Curso encontrado", service.toResponse(c)); }

    // Editar curso (cambiar profesor)
    // PUT /api/cursos/{id}
    @PutMapping("/{id}")
    public ApiResponse<CursoDto.CursoResponse> editar(@PathVariable Integer id, @RequestBody CursoDto.CursoRequest req) {
        Curso editado = service.editar(id, req.nombre());
        return ApiResponse.ok("Curso modificado correctamente", service.toResponse(editado));
    }

    // Borrar un curso
    // DELETE /api/cursos/{id}
    @DeleteMapping("/{id}")
    public ApiResponse<Void> borrar(@PathVariable Integer id) {
        service.borrar(id);
        return ApiResponse.ok("Curso borrado correctamente", null);
    }
}
