package com.ucsm.proserge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EppAdapter extends RecyclerView.Adapter<EppAdapter.ViewHolder> {
    private List<Epp> eppList;
    private Context context;

    public EppAdapter(Context context, List<Epp> eppList) {
        this.context = context;
        this.eppList = eppList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_epp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Epp epp = eppList.get(position);
        holder.bind(epp);
    }

    @Override
    public int getItemCount() {
        return eppList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private Button btnEditar;
        private Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombreEpp);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }

        public void bind(final Epp epp) {
            textViewNombre.setText(epp.getNombre());

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Implementar la lógica para editar
                }
            });

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Implementar la lógica para eliminar
                }
            });
        }
    }
}
