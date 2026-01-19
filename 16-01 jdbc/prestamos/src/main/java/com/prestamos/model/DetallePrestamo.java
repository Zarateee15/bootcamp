package com.prestamos.model;

public class DetallePrestamo {
    private int idDetalle;
    private int idPrestamo;
    private int cantidad;

    public DetallePrestamo(int idDetalle, int idPrestamo, int cantidad) {
        this.idDetalle = idDetalle;
        this.idPrestamo = idPrestamo;
        this.cantidad = cantidad;
    }

    public int getIdDetalle() { return idDetalle; }
    public int getIdPrestamo() { return idPrestamo; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return  idDetalle +
                "\t\t" + cantidad;
    }
}
