package com.example.alumnosfragment;

import java.io.Serializable;

public interface IAlumnosListener extends Serializable {
    void onAlumnoSeleccionado(int id);
}
