package com.prestamos.model;

public class PrestamoLibro {
    private int idDetalle;
    private int idLibro;

    public PrestamoLibro(int idDetalle, int idLibro) {
        this.idDetalle = idDetalle;
        this.idLibro = idLibro;
    }

    public int getIdDetalle() { return idDetalle; }
    public int getIdLibro() { return idLibro; }

    @Override
    public String toString() {
        return "PrestamoLibro{idDetalle=" + idDetalle + ", idLibro=" + idLibro + "}";
    }
}
