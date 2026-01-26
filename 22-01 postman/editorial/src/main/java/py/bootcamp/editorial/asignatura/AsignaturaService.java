package py.bootcamp.editorial.asignatura;

import java.util.List;


public interface AsignaturaService {
    Asignatura crear(AsignaturaDto.AsignaturaRequest request);
    List<AsignaturaDto.AsignaturaResponse> listar();
    Asignatura obtenerPorId(Integer id);
    Asignatura editar (Integer id, String nombre);
    void borrar (Integer id);
    void validarNombre (String nombre);
    AsignaturaDto.AsignaturaResponse toResponse (Asignatura c);
}
