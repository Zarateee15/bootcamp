package com.prestamos.model;

public class Prestamo {
    private int idPrestamo;
    private String fechaPrestamo; // si querés, esto debería ser LocalDate
    private int idProfesor;

    public Prestamo(int idPrestamo, String fechaPrestamo, int idProfesor) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.idProfesor = idProfesor;
    }

    public int getIdPrestamo() { return idPrestamo; }
    public String getFechaPrestamo() { return fechaPrestamo; }
    public int getIdProfesor() { return idProfesor; }

    @Override
    public String toString() {
        return "Prestamo{idPrestamo=" + idPrestamo +
                ", fechaPrestamo='" + fechaPrestamo + "'" +
                ", idProfesor=" + idProfesor + "}";
    }
}
