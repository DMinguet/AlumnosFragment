package com.example.alumnosfragment.parseradapaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumnosfragment.R;
import com.example.alumnosfragment.modelos.Asignatura;
import com.example.alumnosfragment.modelos.Nota;

public class AdaptadorAsignaturas extends RecyclerView.Adapter<AdaptadorAsignaturas.ViewHolder> {
    private Nota[] notas;

    public AdaptadorAsignaturas(Nota[] notas) {
        this.notas = notas;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_asignatura, parent, false);

        return new AdaptadorAsignaturas.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Nota nota = notas[position];
        holder.bindNota(nota);
    }

    public int getItemCount() {
        if (notas == null) {
            return 0;
        } else {
            return notas.length;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvCodigoAsignatura;
        public final TextView tvNota;
        public final TextView tvNombreAsignatura;
        Asignatura[] asignaturas;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCodigoAsignatura = itemView.findViewById(R.id.tvCodigoAsignatura);
            this.tvNota = itemView.findViewById(R.id.tvNota);
            this.tvNombreAsignatura = itemView.findViewById(R.id.tvNombreAsignatura);
        }

        public void bindNota(Nota nota) {
            this.asignaturas = obtenerAsignaturas(tvNota.getContext());

            tvCodigoAsignatura.setText(nota.getCodigoAsignatura());
            tvNota.setText(String.valueOf(nota.getCalificacion()));
            for (int i = 0; i < asignaturas.length; i++) {
                if (asignaturas[i].getCodigoAsignatura().equalsIgnoreCase(nota.getCodigoAsignatura())) {
                    tvNombreAsignatura.setText(asignaturas[i].getNombreAsignatura());
                }
            }
        }

        private Asignatura[] obtenerAsignaturas(Context context) {
            Asignatura[] asignaturas;
            ParserAsignaturas parserAsignaturas = new ParserAsignaturas(context);
            parserAsignaturas.parse();
            asignaturas = parserAsignaturas.getAsignaturas();
            return asignaturas;
        }
    }
}
