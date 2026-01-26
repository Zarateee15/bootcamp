package py.bootcamp.editorial.prestamo;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    public Prestamo registrarPrestamo(PrestamoDto.PrestamoRequest request) {

        // Valida que los campos Profesor y Detalles (libros a prestar) no vengan nulos
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los datos no puede estar vacios");
        }
        if (request.idProfesor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID del profesor no puede estar vacio");
        }
        if (request.items() == null || request.items().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe haber por lo menos un detalle");
        }

        // Valida el/los detalles
        for (PrestamoDto.PrestamoDetalleRequest detalle : request.items()) {
            if (detalle == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe haber por lo menos un detalle");
            }
            if (detalle.idLibro() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del libro no puede estar vacio");
            }
            if (detalle.cantidad() == null || detalle.cantidad() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los datos no puede estar vacios" + detalle.idLibro());
            }
        }

        // Valida que exista el profesor
        Profesor profesor = profesorRepo.findById(request.idProfesor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profesor no existe: " + request.idProfesor()));

        //-------------------------------------------------
        //                 Crea el prestamo
        //-------------------------------------------------

        Prestamo prestamo = new Prestamo();
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setIdProfesor(profesor);
        prestamo = prestamoRepo.save(prestamo);

        // 2) Recorre los libros a alquilar
        for (PrestamoDto.PrestamoDetalleRequest detalle : request.items()) {

            // Valida que el libro seleccionado exista
            Libro libro = libroRepo.findById(detalle.idLibro())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Libro no existe: " + detalle.idLibro()));

            // Valida que haya disponible la cantidad de libros solicitada
            if (libro.getCantidadCopias() < detalle.cantidad()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "No hay copias suficientes de " + libro.getId() +
                                ". Disponibles=" + libro.getCantidadCopias() +
                                ", solicitadas=" + detalle.cantidad()
                );
            }

            // Resta la cantidad disponible del libro
            libro.setCantidadCopias(libro.getCantidadCopias() - detalle.cantidad());
            libroRepo.save(libro);

            //-------------------------------------------------
            //       Inserta el/los detalles al prestamo
            //-------------------------------------------------

            DetallePrestamo det = new DetallePrestamo();
            det.setIdPrestamo(prestamo);
            det.setIdLibro(libro);
            det.setCantidad(detalle.cantidad());
            detalleRepo.save(det);
        }

        return prestamo;
    }

    @Override
    public List<PrestamoDto.PrestamoResponse> listar() {
        return prestamoRepo.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public Prestamo obtenerPorId(Integer id) {
        return prestamoRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Prestamo no encontrado" + id
                    ));
    }

    @Override
    @Transactional
    public Prestamo editarPrestamo (Integer id, Integer idProfe) {
        if (idProfe == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El idProfe es obligatorio");
        }

        Profesor profesor = profesorRepo.findById(idProfe)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Profesor no existe: " + idProfe
                ));

        Prestamo prestamo = obtenerPorId(id);
        prestamo.setIdProfesor(profesor);
        return prestamoRepo.save(prestamo);
    }

    @Override
    @Transactional
    public void borrar (Integer id) {
        Prestamo prestamo = obtenerPorId(id);

        List<DetallePrestamo> detalles = prestamo.getDetalles();
        if (detalles != null) {
            for (DetallePrestamo detalle : detalles) {
                if (detalle != null && detalle.getIdLibro() != null && detalle.getCantidad() != null) {
                    Libro libro = detalle.getIdLibro();
                    libro.setCantidadCopias(libro.getCantidadCopias() + detalle.getCantidad());
                    libroRepo.save(libro);
                }
            }
            // Evita error por FK: borra detalles primero
            detalleRepo.deleteAll(detalles);
        }
        prestamoRepo.delete(prestamo);
    }

    // Helper para formatear el JSON
    public PrestamoDto.PrestamoResponse toResponse(Prestamo p) {
        List<PrestamoDto.PrestamoDetalleResponse> detalles = (p.getDetalles() == null) ? List.of() // Si el prestamo no tiene detalles hace una lista vacia []
                : p.getDetalles().stream()
                .map(d -> new PrestamoDto.PrestamoDetalleResponse(
                        d.getIdLibro().getNombre(),
                        d.getCantidad()
                ))
                .toList();

        return new PrestamoDto.PrestamoResponse(
                p.getFechaPrestamo(),
                p.getIdProfesor().getNombre(),
                p.getIdProfesor().getCedula(),
                detalles
        );
    }

}
