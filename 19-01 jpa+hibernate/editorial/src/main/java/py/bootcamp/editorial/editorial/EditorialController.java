package py.bootcamp.editorial.editorial;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EditorialController {

    private final EditorialService service;

    public EditorialController(EditorialService service) {
        this.service = service;
    }

    public List<Editorial> listar() {
        return service.listar();
    }

    public Editorial crear(String nombre) {
        return service.crear(nombre);
    }
}
