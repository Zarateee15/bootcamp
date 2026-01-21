package py.bootcamp.editorial.libro;

import org.springframework.stereotype.Service;
import py.bootcamp.editorial.editorial.Editorial;
import py.bootcamp.editorial.editorial.EditorialRepository;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository repo;
    private final EditorialRepository editorialRepo;

    public LibroService(LibroRepository repo, EditorialRepository editorialRepo) {
        this.repo = repo;
        this.editorialRepo = editorialRepo;
    }

    public List<Libro> listar() {
        return repo.findAll();
    }

    public Libro crear(String nombre, Integer cantidadCopias, Integer idEditorial) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (cantidadCopias == null || cantidadCopias <= 0  ) {
            throw new IllegalArgumentException("La cantidad no puede estar vacia y ser menor o igual a cero");
        }

        Editorial editorial = editorialRepo.findById(idEditorial)
                .orElseThrow(() -> new IllegalArgumentException("Editorial no existe: " + idEditorial));

        Libro l = new Libro();
        l.setNombre(nombre.trim());
        l.setCantidadCopias(cantidadCopias);
        l.setIdEditorial(editorial);

        return repo.save(l);
    }
}
