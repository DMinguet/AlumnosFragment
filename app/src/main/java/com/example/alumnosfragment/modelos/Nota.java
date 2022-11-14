package com.example.alumnosfragment.modelos;

import java.io.Serializable;

public class Nota implements Serializable {
    private final String codigoAsignatura;
    private final double calificacion;

    public Nota(String codigoAsignatura, double calificacion) {
        this.codigoAsignatura = codigoAsignatura;
        this.calificacion = calificacion;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public double getCalificacion() {
        return calificacion;
    }
}
