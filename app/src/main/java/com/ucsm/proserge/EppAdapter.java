package com.ucsm.proserge;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.ucsm.proserge.Fragments.EditEppsFragment;

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
        private TextView textViewTipo;
        private TextView textViewClasificacion;
        private Button btnEditar;
        private Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombreEpp);
            textViewTipo = itemView.findViewById(R.id.textViewTipoEpp);
            textViewClasificacion = itemView.findViewById(R.id.textViewClasificacionEpp);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }

        public void bind(final Epp epp) {
            textViewNombre.setText(epp.getNombre());
            textViewTipo.setText(epp.getTipo());
            textViewClasificacion.setText(epp.getClasificacion());

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    //Redireccionamiento a EditEppsFragment con valores del registro
                    if (position != RecyclerView.NO_POSITION) {
                        Epp eppToEdit = eppList.get(position);

                        // Redirecciona al fragmento EditEppsFragment con valores de registro
                        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                        EditEppsFragment editEppsFragment = new EditEppsFragment();

                        // Envía los valores del registro al fragmento
                        Bundle bundle = new Bundle();
                        bundle.putString("nombre", eppToEdit.getNombre());
                        bundle.putString("tipo", eppToEdit.getTipo());
                        bundle.putString("clasificacion", eppToEdit.getClasificacion());
                        editEppsFragment.setArguments(bundle);

                        fragmentManager.beginTransaction()
                                .replace(R.id.container, editEppsFragment)
                                .addToBackStack(null)
                                .commit();
                    }
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
