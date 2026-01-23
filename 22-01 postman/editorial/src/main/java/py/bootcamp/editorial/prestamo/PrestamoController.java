package py.bootcamp.editorial.prestamo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrestamoController {

    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    public List<Prestamo> listar() {
        return service.listar();
    }

    public Prestamo registrar(Integer idProfesor, List<LibroPrestamoItem> libros) {
        return service.registrarPrestamo(idProfesor, libros);
    }
}
