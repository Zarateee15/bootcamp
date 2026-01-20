package py.bootcamp.editorial.libro;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroService {

    private final LibroRepository repo;

    public LibroService(LibroRepository repo) {
        this.repo = repo;
    }

    public List<Libro> listar() {
        return repo.findAll();
    }

    public Libro crear(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        Libro l = new Libro();
        l.setNombre(nombre.trim());
        return repo.save(l);
    }
}
