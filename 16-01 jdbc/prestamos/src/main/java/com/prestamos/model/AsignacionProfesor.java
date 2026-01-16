package com.prestamos.model;

public class AsignacionProfesor {
    private int idColegio;
    private int idAsignatura;
    private int idAula;
    private int idCurso;
    private int idProfesor;

    public AsignacionProfesor(int idColegio, int idAsignatura, int idAula, int idCurso, int idProfesor) {
        this.idColegio = idColegio;
        this.idAsignatura = idAsignatura;
        this.idAula = idAula;
        this.idCurso = idCurso;
        this.idProfesor = idProfesor;
    }

    public int getIdColegio() { return idColegio; }
    public int getIdAsignatura() { return idAsignatura; }
    public int getIdAula() { return idAula; }
    public int getIdCurso() { return idCurso; }
    public int getIdProfesor() { return idProfesor; }

    @Override
    public String toString() {
        return "AsignacionProfesor{idColegio=" + idColegio +
                ", idAsignatura=" + idAsignatura +
                ", idAula=" + idAula +
                ", idCurso=" + idCurso +
                ", idProfesor=" + idProfesor + "}";
    }
}
