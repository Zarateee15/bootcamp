package py.bootcamp.editorial.prestamo;

import java.util.List;

public interface PrestamoService {
    Prestamo registrarPrestamo(PrestamoDto.PrestamoRequest request);
    List<PrestamoDto.PrestamoResponse> listar();
    Prestamo obtenerPorId(Integer id);
    Prestamo editarPrestamo (Integer id, Integer idProfe, Integer idColegio, Integer idAsignatura, Integer idAula, Integer idCurso);
    void borrar (Integer id);
    PrestamoDto.PrestamoResponse toResponse(Prestamo p);
}
