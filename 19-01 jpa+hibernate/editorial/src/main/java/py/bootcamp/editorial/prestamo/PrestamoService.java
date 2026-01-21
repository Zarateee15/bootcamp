package py.bootcamp.editorial.prestamo;


import java.util.List;

public interface PrestamoService {
    Prestamo registrarPrestamo(Integer idProfesor, List<LibroPrestamoItem> items);

    List<Prestamo> listar();
}
