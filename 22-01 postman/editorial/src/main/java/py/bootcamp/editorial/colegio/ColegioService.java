package py.bootcamp.editorial.colegio;

import java.util.List;

public interface ColegioService {
    Colegio crear(ColegioDto.ColegioRequest request);
    List<ColegioDto.ColegioResponse> listar();
    Colegio obtenerPorId(Integer id);
    Colegio editar (Integer id, String nombre);
    void borrar (Integer id);
    void validarNombre (String nombre);
    ColegioDto.ColegioResponse toResponse (Colegio c);
}