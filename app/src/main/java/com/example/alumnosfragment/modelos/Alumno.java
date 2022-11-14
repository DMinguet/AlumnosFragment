package com.example.alumnosfragment.modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.GregorianCalendar;

public class Alumno implements Serializable {
    private final int nia;
    private final String nombre;
    private final String primerApellido;
    private final String segundoApellido;
    private final GregorianCalendar fechaNacimiento;
    private final String email;
    private final Nota[] notas;

    public Alumno(int nia, String nombre, String primerApellido, String segundoApellido, GregorianCalendar fechaNacimiento, String email, Nota[] notas) {
        this.nia = nia;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.notas = notas;
    }

    public int getNia() {
        return nia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public Nota[] getNotas() {
        return notas;
    }

    public int getEdad() {
        GregorianCalendar fechaNacimiento = this.fechaNacimiento;

        LocalDate hoy = LocalDate.now();
        LocalDate nacimiento = fechaNacimiento.toZonedDateTime().toLocalDate();
        Period p;

        p = Period.between(nacimiento, hoy);
        return p.getYears();
    }
}