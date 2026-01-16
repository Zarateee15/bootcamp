package prestamos.libros.model;

public class Colegio {
    private int idColegio;
    private String nombre;

    public Colegio(int idColegio, String nombre) {
        this.idColegio = idColegio;
        this.nombre = nombre;
    }

    public int getIdColegio() { return idColegio; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Colegio{idColegio=" + idColegio + ", nombre='" + nombre + "'}";
    }
}
