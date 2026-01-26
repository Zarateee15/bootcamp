package py.bootcamp.editorial.editorial;

import java.util.List;

public interface EditorialService {
    Editorial crear(EditorialDto.EditorialRequest request);
    List<EditorialDto.EditorialResponse> listar();
    Editorial obtenerPorId(Integer id);
    Editorial editar (Integer id, String nombre);
    void borrar (Integer id);
    void validarNombre (String nombre);
    EditorialDto.EditorialResponse toResponse (Editorial c);
}