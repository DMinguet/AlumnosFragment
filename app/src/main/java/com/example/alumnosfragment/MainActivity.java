package com.example.alumnosfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.alumnosfragment.fragments.FragmentDetalle;
import com.example.alumnosfragment.fragments.FragmentListado;
import com.example.alumnosfragment.modelos.Alumno;
import com.example.alumnosfragment.parseradapaters.ParserAlumnos;

public class MainActivity extends AppCompatActivity implements IAlumnosListener, FragmentListado.IOnAttachListener, FragmentDetalle.IOnAttachListener {
    public static final String STUDENTS_KEY = "com.example.alumnos";
    public static final String SELECTED_STUDENT_KEY = "com.example.alumnoseleccionado";
    private FragmentDetalle frgDetalle;
    private boolean tabletLayout;
    private Alumno[] alumnos;
    private Alumno alumnoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            alumnos = (Alumno[]) savedInstanceState.getSerializable(STUDENTS_KEY);
            alumnoSeleccionado = (Alumno) savedInstanceState.getSerializable(SELECTED_STUDENT_KEY);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Alumnos de la clase");

        tabletLayout = findViewById(R.id.FrgDetalle) != null;
        if (tabletLayout) {
            FragmentManager manager = getSupportFragmentManager();
            frgDetalle = (FragmentDetalle) manager.findFragmentById(R.id.FrgDetalle);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(STUDENTS_KEY, alumnos);
        outState.putSerializable(SELECTED_STUDENT_KEY, alumnoSeleccionado);
        super.onSaveInstanceState(outState);
    }

    public void loadData() {
        ParserAlumnos parserAlumnos = new ParserAlumnos(this);

        if (parserAlumnos.parse()) {
            alumnos = parserAlumnos.getAlumnos();
        } else {
            Toast.makeText(this, "Error al obtener los alumnos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAlumnoSeleccionado(int id) {
        alumnoSeleccionado = alumnos[id];
        if(tabletLayout) {
            frgDetalle.mostrarDetalle(alumnoSeleccionado);
        } else {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.FrgListado, FragmentDetalle.class, null)
                    .commit();
        }
    }

    @Override
    public Alumno[] getAlumnos() {
        if (alumnos == null) {
            loadData();
        }
        return alumnos;
    }

    @Override
    public Alumno getAlumno() {
        if (alumnoSeleccionado == null) {
            alumnoSeleccionado = alumnos[0];
        }
        setTitle(alumnoSeleccionado.getNombre() + " " + alumnoSeleccionado.getPrimerApellido());
        return alumnoSeleccionado;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setTitle("Alumnos de la clase");
    }
}