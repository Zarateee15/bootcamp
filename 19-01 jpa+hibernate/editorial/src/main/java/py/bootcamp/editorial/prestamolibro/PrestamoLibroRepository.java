package py.bootcamp.editorial.prestamolibro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoLibroRepository extends JpaRepository<PrestamoLibro, PrestamoLibroId> {


    List<PrestamoLibro> findByIdPrestamoId(Integer prestamoId);
}
