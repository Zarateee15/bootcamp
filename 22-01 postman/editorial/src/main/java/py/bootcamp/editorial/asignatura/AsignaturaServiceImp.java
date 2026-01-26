package py.bootcamp.editorial.asignatura;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AsignaturaServiceImp implements AsignaturaService {

    private final AsignaturaRepository repo;

    public AsignaturaServiceImp(AsignaturaRepository repo) {
        this.repo = repo;
    }

    public List<AsignaturaDto.AsignaturaResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public Asignatura crear(AsignaturaDto.AsignaturaRequest request) {
        validarNombre(request.nombre());
        Asignatura a = new Asignatura();
        a.setNombre(request.nombre().trim());
        return repo.save(a);
    }

    public Asignatura obtenerPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Asignatura no encontrada: " + id
                ));
    }

    public Asignatura editar (Integer id, String nombre) {
        validarNombre(nombre);
        Asignatura a = obtenerPorId(id);
        a.setNombre(nombre);
        return repo.save(a);
    }

    public void borrar (Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrado: " + id);
        }
        repo.deleteById(id);
    }

    public void validarNombre (String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre no puede estar vacio");
        }
    }

    // Helper para formatear el JSON
    public AsignaturaDto.AsignaturaResponse toResponse(Asignatura c) {
        return new AsignaturaDto.AsignaturaResponse(
                c.getId(),
                c.getNombre()
        );
    }
}
