package py.bootcamp.editorial.asignatura;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AsignaturaController {

    private final AsignaturaService service;

    public AsignaturaController(AsignaturaService service) {
        this.service = service;
    }

    public List<Asignatura> listar() {
        return service.listar();
    }

    public Asignatura crear(String nombre) {
        return service.crear(nombre);
    }
}
