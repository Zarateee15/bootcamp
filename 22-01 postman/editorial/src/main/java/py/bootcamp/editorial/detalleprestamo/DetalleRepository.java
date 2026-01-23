package py.bootcamp.editorial.detalleprestamo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetalleRepository extends JpaRepository<DetallePrestamo, Integer> {
    List<DetallePrestamo> findByIdPrestamo(Integer idPrestamo);
}
