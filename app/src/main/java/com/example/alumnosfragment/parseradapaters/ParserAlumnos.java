package com.example.alumnosfragment.parseradapaters;

import android.content.Context;

import com.example.alumnosfragment.R;
import com.example.alumnosfragment.modelos.Alumno;
import com.example.alumnosfragment.modelos.Nota;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ParserAlumnos {
    private Alumno[] alumnos;
    private final InputStream alumnosArchivo;

    public ParserAlumnos(Context context) {
        this.alumnosArchivo = context.getResources().openRawResource(R.raw.alumnos_notas);
    }

    public boolean parse() {
        boolean parsed = false;
        alumnos = null;
        String jsonAlumnos;

        try {
            int size = alumnosArchivo.available();
            byte[] buffer = new byte[size];
            alumnosArchivo.read(buffer);
            alumnosArchivo.close();
            jsonAlumnos = new String(buffer, "UTF-8");
            JSONTokener tokener = new JSONTokener(jsonAlumnos);
            JSONArray arrAlumnos = new JSONArray(tokener);
            alumnos = new Alumno[arrAlumnos.length()];

            for (int i = 0; i < arrAlumnos.length(); i++) {
                int nia = arrAlumnos.getJSONObject(i).getInt("nia");
                String nombre = arrAlumnos.getJSONObject(i).getString("nombre");
                String primerApellido = arrAlumnos.getJSONObject(i).getString("apellido1");
                String segundoApellido = arrAlumnos.getJSONObject(i).getString("apellido2");
                String fechaNacimientoString = arrAlumnos.getJSONObject(i).getString("fechaNacimiento");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(fechaNacimientoString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                GregorianCalendar fechaNacimiento = new GregorianCalendar();
                fechaNacimiento.setTime(date);
                String email = arrAlumnos.getJSONObject(i).getString("email");
                JSONArray arrNotas = arrAlumnos.getJSONObject(i).getJSONArray("notas");
                Nota[] notas = new Nota[arrNotas.length()];
                for (int s = 0; s < arrNotas.length(); s++) {
                    String codigoAsignatura = arrNotas.getJSONObject(s).getString("codAsig");
                    double calificacion = arrNotas.getJSONObject(s).getDouble("calificacion");
                    notas[s] = new Nota(codigoAsignatura, calificacion);
                }
                alumnos[i] = new Alumno(nia, nombre, primerApellido, segundoApellido, fechaNacimiento, email, notas);
            }

            parsed = true;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return parsed;
    }

    public Alumno[] getAlumnos() {
        return this.alumnos;
    }
}
