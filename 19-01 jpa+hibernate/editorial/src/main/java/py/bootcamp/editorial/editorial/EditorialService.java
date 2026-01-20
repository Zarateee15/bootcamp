package py.bootcamp.editorial.editorial;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EditorialService {

    private final EditorialRepository repo;

    public EditorialService(EditorialRepository repo) {
        this.repo = repo;
    }

    public List<Editorial> listar() {
        return repo.findAll();
    }

    public Editorial crear(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        Editorial e = new Editorial();
        e.setNombre(nombre.trim());
        return repo.save(e);
    }
}
