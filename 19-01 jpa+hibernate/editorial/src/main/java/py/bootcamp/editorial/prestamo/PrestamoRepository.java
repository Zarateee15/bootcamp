package py.bootcamp.editorial.prestamo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    // Esto intenta traer también los detalles en el mismo query (si tu Prestamo tiene la relación "detalles")
    @EntityGraph(attributePaths = {"detalles", "detalles.libro", "profesor"})
    List<Prestamo> findAll();
}
