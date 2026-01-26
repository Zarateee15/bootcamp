package py.bootcamp.editorial.aula;
import java.util.List;

public interface AulaService {
    Aula crear(AulaDto.AulaRequest request);
    List<AulaDto.AulaResponse> listar();
    Aula obtenerPorId(Integer id);
    Aula editar (Integer id, String nombre);
    void borrar (Integer id);
    void validarNombre (String nombre);
    AulaDto.AulaResponse toResponse (Aula c);
}