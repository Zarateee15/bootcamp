package py.bootcamp.editorial.editorial;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EditorialServiceImp implements EditorialService {

    private final EditorialRepository repo;

    public EditorialServiceImp(EditorialRepository repo) {
        this.repo = repo;
    }

    public List<EditorialDto.EditorialResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public Editorial crear(EditorialDto.EditorialRequest request) {
        validarNombre(request.nombre());
        Editorial a = new Editorial();
        a.setNombre(request.nombre().trim());
        return repo.save(a);
    }

    public Editorial obtenerPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Editorial no encontrada: " + id
                ));
    }

    public Editorial editar (Integer id, String nombre) {
        validarNombre(nombre);
        Editorial a = obtenerPorId(id);
        a.setNombre(nombre);
        return repo.save(a);
    }

    public void borrar (Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Editorial no encontrado: " + id);
        }
        repo.deleteById(id);
    }

    public void validarNombre (String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre no puede estar vacio");
        }
    }

    // Helper para formatear el JSON
    public EditorialDto.EditorialResponse toResponse(Editorial c) {
        return new EditorialDto.EditorialResponse(
                c.getId(),
                c.getNombre()
        );
    }
}
