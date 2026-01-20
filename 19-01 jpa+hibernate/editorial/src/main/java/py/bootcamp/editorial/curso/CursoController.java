package py.bootcamp.editorial.curso;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    public List<Curso> listar() {
        return service.listar();
    }

    public Curso crear(String nombre) {
        return service.crear(nombre);
    }
}
