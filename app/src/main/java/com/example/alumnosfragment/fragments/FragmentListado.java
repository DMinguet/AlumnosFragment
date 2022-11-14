package com.example.alumnosfragment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumnosfragment.parseradapaters.AdaptadorAlumnos;
import com.example.alumnosfragment.IAlumnosListener;
import com.example.alumnosfragment.R;
import com.example.alumnosfragment.modelos.Alumno;

public class FragmentListado extends Fragment {
    public interface IOnAttachListener {
        Alumno[] getAlumnos();
    }

    private Alumno[] alumnos;
    private IAlumnosListener listener;

    public FragmentListado() {
        super(R.layout.fragment_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdaptadorAlumnos adaptadorAlumnos = new AdaptadorAlumnos(alumnos);
        adaptadorAlumnos.setListener(listener);
        requireActivity().setTitle("Alumnos del curso");
        RecyclerView rvListado = view.findViewById(R.id.rvListado);
        rvListado.setAdapter(adaptadorAlumnos);
        rvListado.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (IAlumnosListener) context;
        IOnAttachListener attachListener = (IOnAttachListener) context;
        alumnos = attachListener.getAlumnos();
    }

    public void setAlumnosListener(Alumno[] alumnos, IAlumnosListener listener) {
        this.alumnos = alumnos;
        this.listener = listener;
    }
}