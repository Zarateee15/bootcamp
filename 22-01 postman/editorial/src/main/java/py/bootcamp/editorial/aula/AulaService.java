package py.bootcamp.editorial.aula;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AulaService {

    private final AulaRepository repo;

    public AulaService(AulaRepository repo) {
        this.repo = repo;
    }

    public List<Aula> listar() {
        return repo.findAll();
    }

    public Aula crear(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        Aula a = new Aula();
        a.setNombre(nombre.trim());
        return repo.save(a);
    }
}
