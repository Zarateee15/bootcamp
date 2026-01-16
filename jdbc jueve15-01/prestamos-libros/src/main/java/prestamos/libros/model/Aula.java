package prestamos.libros.model;

public class Aula {
    private int idAula;
    private String nombre;

    public Aula(int idAula, String nombre) {
        this.idAula = idAula;
        this.nombre = nombre;
    }

    public int getIdAula() { return idAula; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Aula{idAula=" + idAula + ", nombre='" + nombre + "'}";
    }
}
