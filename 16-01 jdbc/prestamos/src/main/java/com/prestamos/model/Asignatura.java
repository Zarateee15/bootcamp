package com.prestamos.model;

public class Asignatura {
    private int idAsignatura;
    private String nombre;

    public Asignatura(int idAsignatura, String nombre) {
        this.idAsignatura = idAsignatura;
        this.nombre = nombre;
    }

    public int getIdAsignatura() { return idAsignatura; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Asignatura{idAsignatura=" + idAsignatura + ", nombre='" + nombre + "'}";
    }
}
