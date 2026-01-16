package com.prestamos.model;

public class Editorial {
    private int idEditorial;
    private String nombre;

    public Editorial(int idEditorial, String nombre) {
        this.idEditorial = idEditorial;
        this.nombre = nombre;
    }

    public int getIdEditorial() { return idEditorial; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Editorial{idEditorial=" + idEditorial + ", nombre='" + nombre + "'}";
    }
}
