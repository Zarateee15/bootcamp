package com.prestamos.model;

public class Profesor {
    private int idProfesor;
    private String nombre;
    private long cedula;

    public Profesor(int idProfesor, String nombre, long cedula) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public int getIdProfesor() { return idProfesor; }
    public String getNombre() { return nombre; }
    public long getCedula() { return cedula; }

    @Override
    public String toString() {
        return idProfesor + "\t\t" + nombre + "\t\t"+ cedula;
    }
}
