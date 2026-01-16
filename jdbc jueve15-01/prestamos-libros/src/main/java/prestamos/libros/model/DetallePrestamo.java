package prestamos.libros.model;

public class DetallePrestamo {
    private int idDetalle;
    private int idPrestamo;
    private String cantidad;

    public DetallePrestamo(int idDetalle, int idPrestamo, String cantidad) {
        this.idDetalle = idDetalle;
        this.idPrestamo = idPrestamo;
        this.cantidad = cantidad;
    }

    public int getIdDetalle() { return idDetalle; }
    public int getIdPrestamo() { return idPrestamo; }
    public String getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return "DetallePrestamo{idDetalle=" + idDetalle +
                ", idPrestamo=" + idPrestamo +
                ", cantidad='" + cantidad + "'}";
    }
}
