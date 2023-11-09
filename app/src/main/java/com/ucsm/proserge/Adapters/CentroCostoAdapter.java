package com.ucsm.proserge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsm.proserge.Clases.CentroCosto;
import com.ucsm.proserge.R;

import java.util.List;

public class CentroCostoAdapter extends RecyclerView.Adapter<CentroCostoAdapter.ViewHolder> {
    private List<CentroCosto> centroCostoList;
    private Context context;

    public CentroCostoAdapter(Context context, List<CentroCosto> centroCostoList){
        this.context = context;
        this.centroCostoList = centroCostoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_centrocosto, parent, false);
        return new ViewHolder(view);
    }
}
