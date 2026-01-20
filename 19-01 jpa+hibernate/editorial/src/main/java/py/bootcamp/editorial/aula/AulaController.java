package py.bootcamp.editorial.aula;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AulaController {

    private final AulaService service;

    public AulaController(AulaService service) {
        this.service = service;
    }

    public List<Aula> listar() {
        return service.listar();
    }

    public Aula crear(String nombre) {
        return service.crear(nombre);
    }
}
