package com.ucsm.proserge.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsm.proserge.R;

import java.util.List;

public class DetalleAddEppItem extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_NORMAL_ITEM = 1;
    private static final int VIEW_TYPE_LAST_ITEM = 2;

    private List<Object> itemList; // Reemplaza 'Object' con el tipo de dato de tus elementos

    // Constructor para recibir la lista de elementos
    public DetalleAddEppItem(List<Object> itemList) {
        this.itemList = itemList;
    }

    // ViewHolder para elementos normales
    public class NormalItemViewHolder extends RecyclerView.ViewHolder {
        // Aquí declara los componentes visuales para los elementos normales
        // Ejemplo:
        // TextView textView;
        // ImageView imageView;
        Button btnRemoveEpp;

        public NormalItemViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializa los componentes visuales para los elementos normales
            // Ejemplo:
            // textView = itemView.findViewById(R.id.textView);
            // imageView = itemView.findViewById(R.id.imageView);
            btnRemoveEpp = itemView.findViewById(R.id.btnEliminarEppDetalleOrden);
        }

        // Aquí configura los componentes visuales para los elementos normales
        // utilizando los datos del objeto itemList en la posición 'position'
        public void bind(Object item) {
            // Ejemplo:
            // textView.setText(item.getSomeText());
            // imageView.setImageResource(item.getImageResource());
            btnRemoveEpp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    itemList.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }
    }

    // ViewHolder para el último elemento con botón adicional
    public class LastItemViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public LastItemViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btnAgregarEppDetalleOrden);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Agregar un nuevo elemento a la lista itemList
                    itemList.add(new Object()); // Reemplaza 'Object' con tu tipo de dato

                    // Notificar al adaptador que se agregó un elemento nuevo
                    notifyItemInserted(itemList.size() - 1);
                }
            });
        }

        // Aquí configura el botón adicional o realiza acciones necesarias
        // para el último elemento especial
        public void bind() {
            // Ejemplo:
            // button.setOnClickListener(v -> {
            //     // Acción al hacer clic en el botón
            // });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == itemList.size()) {
            return VIEW_TYPE_LAST_ITEM;
        } else {
            return VIEW_TYPE_NORMAL_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_NORMAL_ITEM) {
            View view = inflater.inflate(R.layout.item_detalleordenepp, parent, false);
            return new NormalItemViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_detalleordenaddepp, parent, false);
            return new LastItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalItemViewHolder) {
            ((NormalItemViewHolder) holder).bind(itemList.get(position));
        } else if (holder instanceof LastItemViewHolder) {
            ((LastItemViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size() + 1;
    }
}
