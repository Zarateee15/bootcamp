package py.bootcamp.editorial.profesor;

import java.math.BigDecimal;
import java.util.List;

public interface ProfesorService {
    Profesor crear(ProfesorDto.ProfesorRequest request);
    List<ProfesorDto.ProfesorResponse> listar();
    Profesor obtenerPorId(Integer id);
    Profesor editar (Integer id, String nombre, BigDecimal cedula);
    void borrar (Integer id);
    void validarNombre (String nombre);
    ProfesorDto.ProfesorResponse toResponse (Profesor c);
}