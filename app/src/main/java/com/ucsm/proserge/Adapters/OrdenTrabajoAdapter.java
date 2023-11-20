package com.ucsm.proserge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsm.proserge.Clases.OrdenTrabajo;
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
