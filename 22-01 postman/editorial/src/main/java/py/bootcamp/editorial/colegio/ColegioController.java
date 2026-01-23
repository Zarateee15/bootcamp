package py.bootcamp.editorial.colegio;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ColegioController {

    private final ColegioService service;

    public ColegioController(ColegioService service) {
        this.service = service;
    }

    public List<Colegio> listar() {
        return service.listar();
    }

    public Colegio crear(String nombre) {
        return service.crear(nombre);
    }

    /* TODO: Agregar estos metodos
    public Optional<Colegio> buscarPorId(Integer id) {
        return service.buscarPorId(id);
    }

    public void eliminar(Integer id) {
        service.eliminar(id);
    }

    public Colegio editar(Integer id, String nuevoNombre) {
        return service.editar(id, nuevoNombre);
    }*/
}
