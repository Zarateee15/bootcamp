package prestamos.libros.model;

public class Curso {
    private int idCurso;
    private String nombre;

    public Curso(int idCurso, String nombre) {
        this.idCurso = idCurso;
        this.nombre = nombre;
    }

    public int getIdCurso() { return idCurso; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Curso{idCurso=" + idCurso + ", nombre='" + nombre + "'}";
    }
}
