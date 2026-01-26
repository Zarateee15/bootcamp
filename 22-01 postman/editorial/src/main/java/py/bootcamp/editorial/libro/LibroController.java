package py.bootcamp.editorial.libro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroServiceImp service;

    public LibroController(LibroServiceImp service) {this.service = service;}

    // Listar todos los libros
    // GET /api/libros
    @GetMapping
    public ApiResponse<List<LibroDto.LibroResponse>> listar() {
        return ApiResponse.ok("Listado de Libros", service.listar());
    }

    // Crear un Libro
    // POST /api/libros
    @PostMapping
    public ResponseEntity<ApiResponse<LibroDto.LibroResponse>> crear(@RequestBody LibroDto.LibroRequest req) {
        Libro creado = service.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Libro creado correctamente", service.toResponse(creado)));
    }

    // Buscar un Libro por su ID
    // GET /api/libros/{id}
    @GetMapping("/{id}")
    public ApiResponse<LibroDto.LibroResponse> obtenerPorId(@PathVariable Integer id) {
        Libro c = service.obtenerPorId(id);
        return ApiResponse.ok("Libro encontrado", service.toResponse(c)); }

    // Editar Libro (cambiar nombre, cantidad de copias y editorial)
    // PUT /api/libros/{id}
    @PutMapping("/{id}")
    public ApiResponse<LibroDto.LibroResponse> editar(@PathVariable Integer id, @RequestBody LibroDto.LibroRequest req) {
        Libro editado = service.editar(id, req.nombre(), req.cantidadCopias(), req.idEditorial());
        return ApiResponse.ok("Libro modificado correctamente", service.toResponse(editado));
    }

    // Borrar un Libro
    // DELETE /api/libros/{id}
    @DeleteMapping("/{id}")
    public ApiResponse<Void> borrar(@PathVariable Integer id) {
        service.borrar(id);
        return ApiResponse.ok("Libro borrado correctamente", null);
    }
}
