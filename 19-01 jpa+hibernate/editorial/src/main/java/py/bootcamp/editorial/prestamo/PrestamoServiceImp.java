package py.bootcamp.editorial.prestamo;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import py.bootcamp.editorial.detalleprestamo.DetallePrestamo;
import py.bootcamp.editorial.detalleprestamo.DetalleRepository;
import py.bootcamp.editorial.libro.Libro;
import py.bootcamp.editorial.libro.LibroRepository;
import py.bootcamp.editorial.profesor.Profesor;
import py.bootcamp.editorial.profesor.ProfesorRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoServiceImp implements PrestamoService{

    private final PrestamoRepository prestamoRepo;
    private final DetalleRepository detalleRepo;
    private final ProfesorRepository profesorRepo;
    private final LibroRepository libroRepo;

    public PrestamoServiceImp(PrestamoRepository prestamoRepo,
                              DetalleRepository detalleRepo,
                              ProfesorRepository profesorRepo,
                              LibroRepository libroRepo) {
        this.prestamoRepo = prestamoRepo;
        this.detalleRepo = detalleRepo;
        this.profesorRepo = profesorRepo;
        this.libroRepo = libroRepo;
    }

    @Override
    @Transactional
    public Prestamo registrarPrestamo(Integer idProfesor,
                                             List<LibroPrestamoItem> libros) {

        // Valida que los campos Profesor y Detalles (libros a prestar) no vengan nulos
        if (idProfesor == null) throw new IllegalArgumentException("El ID del Profesor no puede ser null");
        if (libros == null || libros.isEmpty()) throw new IllegalArgumentException("Tiene que haber al menos 1 detalle");

        // Valida los detalles individualmente
        for (LibroPrestamoItem libro : libros) {
            if (libro == null) throw new IllegalArgumentException("Detalle inválido");
            if (libro.idLibro() == null) throw new IllegalArgumentException("El ID del libro no puede ser null");
            if (libro.cantidad() == null || libro.cantidad() <= 0) {
                throw new IllegalArgumentException("Cantidad inválida para libro " + libro.idLibro());
            }
        }

        // Valida que exista el profesor
        Profesor profesor = profesorRepo.findById(idProfesor)
                .orElseThrow(() -> new IllegalArgumentException("Profesor no existe: " + idProfesor));

        //-------------------------------------------------
        //                 Crea el prestamo
        //-------------------------------------------------

        Prestamo prestamo = new Prestamo();
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setIdProfesor(profesor);
        prestamo = prestamoRepo.save(prestamo);

        // 2) Recorre los libros a alquilar
        for (LibroPrestamoItem item : libros) {

            // Valida que el libro seleccionado exista
            Libro libro = libroRepo.findById(item.idLibro())
                    .orElseThrow(() -> new IllegalArgumentException("Libro no existe: " + item.idLibro()));

            // Valida que haya disponible la cantidad de libros solicitada
            if (libro.getCantidadCopias() < item.cantidad()) {
                throw new IllegalArgumentException(
                        "No hay copias suficientes de " + libro.getId() +
                                ". Disponibles=" + libro.getCantidadCopias() +
                                ", solicitadas=" + item.cantidad()
                );
            }

            // Resta la cantidad disponible del libro
            libro.setCantidadCopias(libro.getCantidadCopias() - item.cantidad());
            libroRepo.save(libro);

            //-------------------------------------------------
            //       Inserta el/los detalles al prestamo
            //-------------------------------------------------

            DetallePrestamo det = new DetallePrestamo();
            det.setIdPrestamo(prestamo);
            det.setIdLibro(libro);
            det.setCantidad(item.cantidad());
            detalleRepo.save(det);
        }

        return prestamo;
    }

    @Override
    public List<Prestamo> listar() {
        return prestamoRepo.findAll();
    }
}
