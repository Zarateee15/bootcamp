package py.bootcamp.editorial.colegio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ColegioRepository extends JpaRepository<Colegio, Integer> {
    // métodos extra opcionales:
    // List<Colegio> findByNombreContainingIgnoreCase(String nombre);
}
