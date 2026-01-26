package py.bootcamp.editorial.curso;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CursoServiceImp implements CursoService {

    private final CursoRepository repo;

    public CursoServiceImp(CursoRepository repo) {
        this.repo = repo;
    }

    public List<CursoDto.CursoResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public Curso crear(CursoDto.CursoRequest request) {
        validarNombre(request.nombre());
        Curso a = new Curso();
        a.setNombre(request.nombre().trim());
        return repo.save(a);
    }

    public Curso obtenerPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Curso no encontrado: " + id
                ));
    }

    public Curso editar (Integer id, String nombre) {
        validarNombre(nombre);
        Curso a = obtenerPorId(id);
        a.setNombre(nombre);
        return repo.save(a);
    }

    public void borrar (Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado: " + id);
        }
        repo.deleteById(id);
    }

    public void validarNombre (String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre no puede estar vacio");
        }
    }

    // Helper para formatear el JSON
    public CursoDto.CursoResponse toResponse(Curso c) {
        return new CursoDto.CursoResponse(
                c.getId(),
                c.getNombre()
        );
    }
}
