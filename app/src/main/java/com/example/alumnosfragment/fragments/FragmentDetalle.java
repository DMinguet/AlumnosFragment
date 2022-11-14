package com.example.alumnosfragment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumnosfragment.parseradapaters.AdaptadorAsignaturas;
import com.example.alumnosfragment.R;
import com.example.alumnosfragment.modelos.Alumno;

public class FragmentDetalle extends Fragment {
    public interface IOnAttachListener {
        Alumno getAlumno();
    }

    private RecyclerView rvDetalle;
    private Alumno alumno;
    private AdaptadorAsignaturas adaptadorAsignaturas;

    public FragmentDetalle() {
        super(R.layout.fragment_detalle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mostrarDetalle(alumno);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        alumno = attachListener.getAlumno();
    }

    public void mostrarDetalle(Alumno alumno) {
        requireActivity().setTitle("Notas de " + alumno.getNombre() + " " + alumno.getPrimerApellido() + " " + alumno.getSegundoApellido());
        rvDetalle = requireView().findViewById(R.id.rvListado);
        rvDetalle.setAdapter(new AdaptadorAsignaturas(alumno.getNotas()));
        rvDetalle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }
}
