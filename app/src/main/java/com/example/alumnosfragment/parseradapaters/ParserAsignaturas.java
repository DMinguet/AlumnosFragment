package com.example.alumnosfragment.parseradapaters;

import android.content.Context;

import com.example.alumnosfragment.R;
import com.example.alumnosfragment.modelos.Asignatura;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

public class ParserAsignaturas {
    private Asignatura[] asignaturas;
    private final InputStream asignaturasArchivo;

    public ParserAsignaturas(Context context) {
        this.asignaturasArchivo = context.getResources().openRawResource(R.raw.asignaturas);
    }

    public boolean parse() {
        boolean parsed = false;
        asignaturas = null;
        String json = null;

        try {
            int size = asignaturasArchivo.available();
            byte[] buffer = new byte[size];
            asignaturasArchivo.read(buffer);
            asignaturasArchivo.close();
            json = new String(buffer, "UTF-8");
            JSONTokener tokener = new JSONTokener(json);
            JSONArray arr = new JSONArray(tokener);
            asignaturas = new Asignatura[arr.length()];

            for (int i = 0; i < arr.length(); i++) {
                String codigoAsignatura = arr.getJSONObject(i).getString("codAsig");
                String nombreAsignatura = arr.getJSONObject(i).getString("nomAsig");
                asignaturas[i] = new Asignatura(codigoAsignatura, nombreAsignatura);
            }

            parsed = true;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return parsed;
    }

    public Asignatura[] getAsignaturas() {
        return this.asignaturas;
    }
}
