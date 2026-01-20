package py.bootcamp.editorial.colegio;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColegioService {

    private final ColegioRepository repo;

    public ColegioService(ColegioRepository repo) {
        this.repo = repo;
    }

    public List<Colegio> listar() {
        return repo.findAll();
    }

    public Colegio crear(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        Colegio c = new Colegio();
        c.setNombre(nombre.trim());
        return repo.save(c);
    }
}
