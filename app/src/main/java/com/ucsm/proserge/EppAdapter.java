package com.ucsm.proserge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

                    // Implementar la lógica para editar
                    int position = getAdapterPosition();

                    //Toast de verificacion
                    if (position != RecyclerView.NO_POSITION) {
                        Epp eppToEdit = eppList.get(position);

                        String mensaje = "Editando " + eppToEdit.getNombre();
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                    }
                    // Redirecciona al fragmento EditEppsFragment
                    FragmentActivity activity = (FragmentActivity) context;
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();

                    // Reemplaza con el fragmento correcto que deseas cargar (EditEppsFragment)
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, new EditEppsFragment()) // Reemplaza con EditEppsFragment
                            .addToBackStack(null)
                            .commit();

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
