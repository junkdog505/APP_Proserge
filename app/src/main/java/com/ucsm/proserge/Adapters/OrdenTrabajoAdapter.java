package com.ucsm.proserge.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsm.proserge.Clases.OrdenTrabajo;
import com.ucsm.proserge.Fragments.DetalleOrdenTrabajoFragment;
import com.ucsm.proserge.R;

import java.util.List;

import kotlin.jvm.internal.Ref;

public class OrdenTrabajoAdapter extends RecyclerView.Adapter<OrdenTrabajoAdapter.ViewHolder>{
    private List<OrdenTrabajo> ordenList;
    private Context context;

    public OrdenTrabajoAdapter(Context context, List<OrdenTrabajo> ordenList){
        this.context = context;
        this.ordenList = ordenList;
    }

    //Metodo para busqueda y actualizacion de lista filtrada
    public void filterList(List<OrdenTrabajo> filteredList){

        //Implementar

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orden, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        OrdenTrabajo ordenTrabajo = ordenList.get(position);
        holder.bind(ordenTrabajo);
        //Verificación de últimmo elemento
        if (position == getItemCount() - 1){
            //Ultimo elemento
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.bottomMargin = (int) holder.itemView.getContext().getResources().getDimension(R.dimen.margin_bottom_last_item);
            holder.itemView.setLayoutParams(layoutParams);
        }else{
            //No es ultimo  elemento
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.bottomMargin = 0;
            holder.itemView.setLayoutParams(layoutParams);
        }

        // Redirección al pulsar cada Cardview
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el registro seleccionado
                OrdenTrabajo ordenSeleccionada = ordenList.get(position);

                // Fragmento para mostrar los detalles del registro
                DetalleOrdenTrabajoFragment detalleFragment = new DetalleOrdenTrabajoFragment();

                // Pasar id del registro al nuevo fragmento por medio de Bundle
                Bundle bundle = new Bundle();
                bundle.putString("id", ordenSeleccionada.getId());
                bundle.putString("fecha", ordenSeleccionada.getFecha());
                bundle.putString("nombre", ordenSeleccionada.getNombre());
                bundle.putString("trabajador_id", ordenSeleccionada.getId_trabajador());
                bundle.putString("trabajador_nombres", ordenSeleccionada.getNombres_trabajador());
                bundle.putString("trabajador_apellidos", ordenSeleccionada.getApellidos_trabajador());
                bundle.putString("trabajador_cargo", ordenSeleccionada.getCargo_trabajador());
                bundle.putString("centrocosto_id", ordenSeleccionada.getId_centrocosto());
                bundle.putString("centrocosto_nombre", ordenSeleccionada.getNombre_centrocosto());
                bundle.putString("tipoentrega", ordenSeleccionada.getId());
                detalleFragment.setArguments(bundle);

                // Abrir el nuevo fragmento con los detalles del registro
                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, detalleFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

    }

    @Override
    public int getItemCount(){
        return ordenList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewId, textViewFecha, textViewNombre, textViewCentroCosto, textViewDni;
        private Button btnEditar, btnEliminar;

        public ViewHolder(View itemView){
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewIdOrden);
            textViewFecha = itemView.findViewById(R.id.textViewFechaOrden);
            textViewNombre = itemView.findViewById(R.id.textViewNombreOrden);
            textViewCentroCosto = itemView.findViewById(R.id.textViewCentroOrden);
            textViewDni = itemView.findViewById(R.id.textViewDniOrden);

            btnEditar = itemView.findViewById(R.id.btnEditarElementoOrden);
            btnEliminar = itemView.findViewById(R.id.btnEliminarElementoOrden);
        }

        public void bind(final OrdenTrabajo ordenTrabajo){
            textViewId.setText(ordenTrabajo.getId());
            textViewFecha.setText(ordenTrabajo.getFecha());
            textViewNombre.setText(ordenTrabajo.getNombre());
            textViewCentroCosto.setText(ordenTrabajo.getId_centrocosto());
            textViewDni.setText(ordenTrabajo.getId_trabajador());

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Implementar
                }
            });

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Implementar
                }
            });
        }
    }

}
