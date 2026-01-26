package py.bootcamp.editorial.curso;

import java.util.List;

public interface CursoService {
    Curso crear(CursoDto.CursoRequest request);
    List<CursoDto.CursoResponse> listar();
    Curso obtenerPorId(Integer id);
    Curso editar (Integer id, String nombre);
    void borrar (Integer id);
    void validarNombre (String nombre);
    CursoDto.CursoResponse toResponse (Curso c);
}
