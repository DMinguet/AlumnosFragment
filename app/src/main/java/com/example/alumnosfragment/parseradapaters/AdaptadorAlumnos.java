package com.example.alumnosfragment.parseradapaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumnosfragment.IAlumnosListener;
import com.example.alumnosfragment.R;
import com.example.alumnosfragment.modelos.Alumno;

public class AdaptadorAlumnos extends RecyclerView.Adapter<AdaptadorAlumnos.ViewHolder> {
    private final Alumno[] alumnos;
    private IAlumnosListener listener;

    public AdaptadorAlumnos(Alumno[] alumnos) {
        this.alumnos = alumnos;
    }

    public void setListener(IAlumnosListener listener) {
        this.listener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_alumno, parent, false);

        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alumno alumno = alumnos[position];
        holder.bindAlumno(alumno);
    }

    public int getItemCount() {
        if (alumnos == null) {
            return 0;
        } else {
            return alumnos.length;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvNombre;
        private final TextView tvPrimerApellido;
        private final TextView tvSegundoApellido;
        private final TextView tvEmail;
        private final TextView tvEdad;

        public ViewHolder(@NonNull View itemView, IAlumnosListener listener) {
            super(itemView);
            this.tvNombre = itemView.findViewById(R.id.tvNombre);
            this.tvPrimerApellido = itemView.findViewById(R.id.tvPrimerApellido);
            this.tvSegundoApellido = itemView.findViewById(R.id.tvSegundoApellido);
            this.tvEmail = itemView.findViewById(R.id.tvEmail);
            this.tvEdad = itemView.findViewById(R.id.tvEdad);
            itemView.setOnClickListener(this);
        }

        public void bindAlumno(Alumno alumno) {
            tvNombre.setText(alumno.getNombre());
            tvPrimerApellido.setText(alumno.getPrimerApellido());
            tvSegundoApellido.setText(alumno.getSegundoApellido());
            tvEmail.setText(alumno.getEmail());
            tvEdad.setText(String.valueOf(alumno.getEdad()));
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onAlumnoSeleccionado(getAdapterPosition());
            }
        }
    }
}
