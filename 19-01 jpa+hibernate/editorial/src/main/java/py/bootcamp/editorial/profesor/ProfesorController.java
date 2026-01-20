package py.bootcamp.editorial.profesor;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    public List<Profesor> listar() {
        return service.listar();
    }

    public Profesor crear(String nombre) {
        return service.crear(nombre);
    }
}
