package py.bootcamp.editorial.curso;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository repo;

    public CursoService(CursoRepository repo) {
        this.repo = repo;
    }

    public List<Curso> listar() {
        return repo.findAll();
    }

    public Curso crear(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        Curso c = new Curso();
        c.setNombre(nombre.trim());
        return repo.save(c);
    }
}
