package py.bootcamp.editorial.profesor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProfesorServiceImp implements ProfesorService {

    private final ProfesorRepository repo;

    public ProfesorServiceImp(ProfesorRepository repo) {
        this.repo = repo;
    }

    public List<ProfesorDto.ProfesorResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public Profesor crear(ProfesorDto.ProfesorRequest request) {
        validarNombre(request.nombre());
        Profesor a = new Profesor();
        a.setNombre(request.nombre().trim());
        a.setCedula(request.cedula());
        return repo.save(a);
    }

    public Profesor obtenerPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Profesor no encontrada: " + id
                ));
    }

    public Profesor editar (Integer id, String nombre, BigDecimal cedula) {
        validarNombre(nombre);
        Profesor a = obtenerPorId(id);
        a.setNombre(nombre);
        a.setCedula(cedula);
        return repo.save(a);
    }

    public void borrar (Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado: " + id);
        }
        repo.deleteById(id);
    }

    public void validarNombre (String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre no puede estar vacio");
        }
    }

    // Helper para formatear el JSON
    public ProfesorDto.ProfesorResponse toResponse(Profesor c) {
        return new ProfesorDto.ProfesorResponse(
                c.getId(),
                c.getNombre(),
                c.getCedula()
        );
    }
}
