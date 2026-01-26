package py.bootcamp.editorial.colegio;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ColegioServiceImp implements ColegioService {

    private final ColegioRepository repo;

    public ColegioServiceImp(ColegioRepository repo) {
        this.repo = repo;
    }

    public List<ColegioDto.ColegioResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public Colegio crear(ColegioDto.ColegioRequest request) {
        validarNombre(request.nombre());
        Colegio a = new Colegio();
        a.setNombre(request.nombre().trim());
        return repo.save(a);
    }

    public Colegio obtenerPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Colegio no encontrada: " + id
                ));
    }

    public Colegio editar (Integer id, String nombre) {
        validarNombre(nombre);
        Colegio a = obtenerPorId(id);
        a.setNombre(nombre);
        return repo.save(a);
    }

    public void borrar (Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Colegio no encontrado: " + id);
        }
        repo.deleteById(id);
    }

    public void validarNombre (String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre no puede estar vacio");
        }
    }

    // Helper para formatear el JSON
    public ColegioDto.ColegioResponse toResponse(Colegio c) {
        return new ColegioDto.ColegioResponse(
                c.getId(),
                c.getNombre()
        );
    }
}
