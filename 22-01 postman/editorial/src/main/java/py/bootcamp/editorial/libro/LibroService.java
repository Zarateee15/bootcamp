package py.bootcamp.editorial.libro;

import java.util.List;

public interface LibroService {
    Libro crear(LibroDto.LibroRequest request);
    List<LibroDto.LibroResponse> listar();
    Libro obtenerPorId(Integer id);
    Libro editar (Integer id, String nombre, Integer cantidadCopias, Integer idEditorial);
    void borrar (Integer id);
    void validarNombre (String nombre);
    LibroDto.LibroResponse toResponse (Libro c);
}