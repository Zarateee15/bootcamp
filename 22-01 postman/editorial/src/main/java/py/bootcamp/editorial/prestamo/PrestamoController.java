package py.bootcamp.editorial.prestamo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.bootcamp.editorial.asignatura.Asignatura;
import py.bootcamp.editorial.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoServiceImp service;

    public PrestamoController(PrestamoServiceImp service) {
        this.service = service;
    }

    // Listar todos los prestamos
    // GET /api/prestamos
    @GetMapping
    public ApiResponse<List<PrestamoDto.PrestamoResponse>> listar() {
        return ApiResponse.ok("Listado de Prestamos", service.listar());
    }

    // Crear un Prestamo
    // POST /api/prestamos
    @PostMapping
    public ResponseEntity<ApiResponse<PrestamoDto.PrestamoResponse>> registrar(@RequestBody PrestamoDto.PrestamoRequest req) {
        Prestamo creado = service.registrarPrestamo(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Préstamo creado correctamente", service.toResponse(creado)));
    }

    // Buscar un prestamo por su ID
    // GET /api/prestamos/{id}
    @GetMapping("/{id}")
    public ApiResponse<PrestamoDto.PrestamoResponse> obtenerPorId(@PathVariable Integer id) {
        Prestamo p = service.obtenerPorId(id);
        return ApiResponse.ok("Préstamo modificado correctamente", service.toResponse(p));
    }

    // Editar préstamo (cambiar profesor)
    // PUT /api/prestamos/{id}
    @PutMapping("/{id}")
    public ApiResponse<PrestamoDto.PrestamoResponse> editar(@PathVariable Integer id, @RequestBody PrestamoDto.EditarPrestamoRequest req) {
        Prestamo editado = service.editarPrestamo(id, req.idProfesor());
        return ApiResponse.ok("Préstamo modificado correctamente", service.toResponse(editado));
    }

    // Borrar un prestamo
    // DELETE /api/prestamos/{id}
    @DeleteMapping("/{id}")
    public ApiResponse<Void> borrar(@PathVariable Integer id) {
        service.borrar(id);
        return ApiResponse.ok("Préstamo borrado correctamente", null);
    }

}
