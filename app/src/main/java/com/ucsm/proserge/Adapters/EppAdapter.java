package com.ucsm.proserge.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.CRUD.EditEppsFragment;
import com.ucsm.proserge.Clases.Trabajador;
import com.ucsm.proserge.R;
import com.ucsm.proserge.Clases.Epp;

import java.util.List;

public class EppAdapter extends RecyclerView.Adapter<EppAdapter.ViewHolder> {
    private List<Epp> eppList;
    private Context context;

    public EppAdapter(Context context, List<Epp> eppList) {
        this.context = context;
        this.eppList = eppList;
    }

    // Método para realizar la búsqueda y actualizar la lista filtrada por DNI
    public void filterList(List<Epp> filteredList) {
        eppList = filteredList;
        notifyDataSetChanged();
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
        // Verifica si el elemento actual es el último
        if (position == getItemCount() - 1) {
            // Último elemento, aplica margen inferior
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.bottomMargin = (int) holder.itemView.getContext().getResources().getDimension(R.dimen.margin_bottom_last_item);
            holder.itemView.setLayoutParams(layoutParams);
        } else {
            // Si no es el último elemento, restablece el margen inferior a 0
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.bottomMargin = 0;
            holder.itemView.setLayoutParams(layoutParams);
        }
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
                        bundle.putInt("id", eppToEdit.getId());
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
                    int position = getAdapterPosition();
                    // Implementar la lógica para eliminar
                    Epp eppToEdit = eppList.get(position);

                    AdminSQLite admin= new AdminSQLite(context);
                    SQLiteDatabase db = admin.getWritableDatabase();

                    String id_to_delete = String.valueOf(eppToEdit.getId()); //Id del elemento a eliminar

                    // Verificación si hay registros relacionados (FK) en otras tablas
                    int relatedRecordsCount = 0;
                    Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM OrdenTrabajo WHERE Id_EPPS=?", new String[]{id_to_delete});
                    if (cursor.moveToFirst()) {
                        relatedRecordsCount = cursor.getInt(0);
                    }
                    cursor.close();

                    if (relatedRecordsCount > 0) {
                        // Hay registros relacionados en otras tablas, muestra un mensaje de error
                        Toast.makeText(context, "Este artículo se encuentra en uso, no se puede eliminar", Toast.LENGTH_SHORT).show();
                    } else {
                        // No hay registros relacionados, procede a eliminar el registro principal
                        int changes = db.delete("EPPS", "Id_epp=?", new String[]{id_to_delete});
                        db.close();
                        if (changes == 1) {
                            Toast.makeText(context, "Artículo eliminado exitosamente", Toast.LENGTH_SHORT).show();
                            eppList.remove(position);
                            notifyItemRemoved(position);
                        } else {
                            Toast.makeText(context, "El Artículo no existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}
