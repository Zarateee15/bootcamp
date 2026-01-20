package py.bootcamp.editorial.profesor;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository repo;

    public ProfesorService(ProfesorRepository repo) {
        this.repo = repo;
    }

    public List<Profesor> listar() {
        return repo.findAll();
    }

    public Profesor crear(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        Profesor p = new Profesor();
        p.setNombre(nombre.trim());
        return repo.save(p);
    }
}
