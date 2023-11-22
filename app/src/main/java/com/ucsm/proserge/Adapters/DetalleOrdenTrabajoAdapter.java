package com.ucsm.proserge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsm.proserge.Clases.DetalleOrdenTrabajo;
import com.ucsm.proserge.R;

import java.util.List;

public class DetalleOrdenTrabajoAdapter extends RecyclerView.Adapter<DetalleOrdenTrabajoAdapter.ViewHolder>{
    private List<DetalleOrdenTrabajo> detalleOrdenTrabajoList;
    private Context context;

    public DetalleOrdenTrabajoAdapter(Context context, List<DetalleOrdenTrabajo> detalleOrdenTrabajoList){
        this.context = context;
        this.detalleOrdenTrabajoList = detalleOrdenTrabajoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        DetalleOrdenTrabajo detalleOrdenTrabajo = detalleOrdenTrabajoList.get(position);
        holder.bind(detalleOrdenTrabajo);
    }

    @Override
    public int getItemCount(){
        return detalleOrdenTrabajoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewDetalleEpp;

        public ViewHolder(View itemView){
            super(itemView);
            textViewDetalleEpp = itemView.findViewById(R.id.textViewDetalleNombreEpp);
        }

        public void bind(final DetalleOrdenTrabajo detalleOrdenTrabajo){
            textViewDetalleEpp.setText(detalleOrdenTrabajo.getEpps_nombre());
        }
    }
}
