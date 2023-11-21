package com.ucsm.proserge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsm.proserge.Clases.DetalleOrdenTrabajo;
import com.ucsm.proserge.R;

import java.util.List;

public class DetalleOrdenTrabajoAdapter extends RecyclerView.Adapter<OrdenTrabajoAdapter.ViewHolder>{
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
}
