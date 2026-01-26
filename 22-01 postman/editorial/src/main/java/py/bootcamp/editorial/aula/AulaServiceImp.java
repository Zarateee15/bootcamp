package py.bootcamp.editorial.aula;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AulaServiceImp implements AulaService {

    private final AulaRepository repo;

    public AulaServiceImp(AulaRepository repo) {
        this.repo = repo;
    }

    public List<AulaDto.AulaResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public Aula crear(AulaDto.AulaRequest request) {
        validarNombre(request.nombre());
        Aula a = new Aula();
        a.setNombre(request.nombre().trim());
        return repo.save(a);
    }

    public Aula obtenerPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Aula no encontrada: " + id
                ));
    }

    public Aula editar (Integer id, String nombre) {
        validarNombre(nombre);
        Aula a = obtenerPorId(id);
        a.setNombre(nombre);
        return repo.save(a);
    }

    public void borrar (Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aula no encontrado: " + id);
        }
        repo.deleteById(id);
    }

    public void validarNombre (String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre no puede estar vacio");
        }
    }

    // Helper para formatear el JSON
    public AulaDto.AulaResponse toResponse(Aula c) {
        return new AulaDto.AulaResponse(
                c.getId(),
                c.getNombre()
        );
    }
}
