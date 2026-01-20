package py.bootcamp.editorial.asignatura;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AsignaturaService {

    private final AsignaturaRepository repo;

    public AsignaturaService(AsignaturaRepository repo) {
        this.repo = repo;
    }

    public List<Asignatura> listar() {
        return repo.findAll();
    }

    public Asignatura crear(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        Asignatura a = new Asignatura();
        a.setNombre(nombre.trim());
        return repo.save(a);
    }
}
