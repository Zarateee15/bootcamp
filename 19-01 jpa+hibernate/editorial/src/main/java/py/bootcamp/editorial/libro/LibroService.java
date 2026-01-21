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

    public Libro crear(String nombre, Integer cantidadCopias) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (cantidadCopias == null || cantidadCopias <= 0  ) {
            throw new IllegalArgumentException("La cantidad no puede estar vacia y ser menor o igual a cero");
        }
        Libro l = new Libro();
        l.setNombre(nombre.trim());
        l.setCantidadCopias(cantidadCopias);

        return repo.save(l);
    }
}
