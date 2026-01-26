package py.bootcamp.editorial.libro;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import py.bootcamp.editorial.editorial.Editorial;
import py.bootcamp.editorial.editorial.EditorialRepository;
import py.bootcamp.editorial.editorial.EditorialDto;


import java.util.List;

@Service
public class LibroServiceImp implements LibroService {

    private final LibroRepository libroRepo;
    private final EditorialRepository editorialRepo;


    public LibroServiceImp(LibroRepository libroRepo, EditorialRepository editorialRepo) {
        this.libroRepo = libroRepo;
        this.editorialRepo = editorialRepo;
    }

    public List<LibroDto.LibroResponse> listar() {
        return libroRepo.findAll().stream().map(this::toResponse).toList();
    }

    public Libro crear(LibroDto.LibroRequest request) {
        validarNombre(request.nombre());

        Editorial editorial = editorialRepo.findById(request.idEditorial())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Editorial no encontrada: " + request.idEditorial()
                    ));

        Libro a = new Libro();
        a.setNombre(request.nombre().trim());
        a.setCantidadCopias(request.cantidadCopias());
        a.setIdEditorial(editorial);
        return libroRepo.save(a);
    }

    public Libro obtenerPorId(Integer id) {
        return libroRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Libro no encontrada: " + id
                ));
    }

    public Libro editar (Integer id, String nombre, Integer cantidadCopias, Integer idEditorial) {
        validarNombre(nombre);
        Editorial editorial = editorialRepo.findById(idEditorial)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Editorial no encontrada: " + idEditorial
                ));

        Libro a = obtenerPorId(id);
        a.setNombre(nombre);
        a.setCantidadCopias(cantidadCopias);
        a.setIdEditorial(editorial);
        return libroRepo.save(a);
    }

    public void borrar (Integer id) {
        if (!libroRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado: " + id);
        }
        libroRepo.deleteById(id);
    }

    public void validarNombre (String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre no puede estar vacio");
        }
    }

    // Helper para formatear el JSON
    public LibroDto.LibroResponse toResponse(Libro c) {
        return new LibroDto.LibroResponse(
                c.getId(),
                c.getNombre(),
                c.getCantidadCopias(),
                new EditorialDto.EditorialResponse(
                        c.getIdEditorial().getId(),
                        c.getIdEditorial().getNombre()
                )
        );
    }
}
