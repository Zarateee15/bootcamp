package py.bootcamp.editorial.libro;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class LibroController {

    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    public List<Libro> listar() {
        return service.listar();
    }

    public Libro crear(String nombre,Integer cantidadCopias) {
        return service.crear(nombre,cantidadCopias);
    }
}
