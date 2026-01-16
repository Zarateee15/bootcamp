package com.prestamos.model;

public class Libro {
    private int idLibro;
    private String nombre;
    private int cantidadCopias;
    private int idEditorial;

    public Libro(int idLibro, String nombre, int cantidadCopias, int idEditorial) {
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.cantidadCopias = cantidadCopias;
        this.idEditorial = idEditorial;
    }

    public int getIdLibro() { return idLibro; }
    public String getNombre() { return nombre; }
    public int getCantidadCopias() { return cantidadCopias; }
    public int getIdEditorial() { return idEditorial; }

    @Override
    public String toString() {
        return "Libro{idLibro=" + idLibro +
                ", nombre='" + nombre + "'" +
                ", cantidadCopias=" + cantidadCopias +
                ", idEditorial=" + idEditorial + "}";
    }
}
