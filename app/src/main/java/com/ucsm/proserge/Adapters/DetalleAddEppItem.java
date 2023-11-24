package com.ucsm.proserge.Adapters;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.Clases.Epp;
import com.ucsm.proserge.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetalleAddEppItem extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_NORMAL_ITEM = 1;
    private static final int VIEW_TYPE_LAST_ITEM = 2;

    private List<Epp> itemList;

    // Constructor para recibir la lista de elementos
    public DetalleAddEppItem(List<Epp> itemList) {
        this.itemList = itemList;
    }

    // Método para cargar opciones en el Spinner
    private void cargarOpcionesSpinner(Spinner spinner) {
        AdminSQLite admin = new AdminSQLite(spinner.getContext());
        SQLiteDatabase db = admin.getReadableDatabase();

        List<Epp> optionsEpps = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT EPPS.Id_epp, EPPS.Nombre FROM EPPS", null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);
                int idint = Integer.parseInt(id);
                Epp epp= new Epp(idint, nombre);
                optionsEpps.add(epp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // Crea un adaptador para el Spinner y asigna las opciones
        ArrayAdapter<Epp> adapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, optionsEpps);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    // ViewHolder para elementos normales
    public class NormalItemViewHolder extends RecyclerView.ViewHolder {
        Button btnRemoveEpp;
        Spinner spinnerEpp;
        TextView textViewAddDetalleEppId;

        public NormalItemViewHolder(@NonNull View itemView) {
            super(itemView);
            btnRemoveEpp = itemView.findViewById(R.id.btnEliminarEppDetalleOrden);
            spinnerEpp = itemView.findViewById(R.id.spinner_epps);
            textViewAddDetalleEppId = itemView.findViewById(R.id.editText_AddDetalleEppId);
        }
        public void bind(Epp item) {
            btnRemoveEpp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    itemList.remove(position);
                    notifyItemRemoved(position);
                }
            });

            cargarOpcionesSpinner(spinnerEpp);

            spinnerEpp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Obtener el ID seleccionado en el Spinner
                    Epp selectedEpp = (Epp) parent.getItemAtPosition(position);
                    int selectedId = selectedEpp.getId();

                    // Establecer el ID seleccionado en el TextView correspondiente
                    textViewAddDetalleEppId.setText(String.valueOf(selectedId));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Acción cuando no se selecciona nada en el Spinner
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
                    itemList.add(new Epp(1, "n")); // Reemplaza 'Object' con tu tipo de dato

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
