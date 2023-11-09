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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.CRUD.EditCentroCostoFragment;
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        CentroCosto centroCosto = centroCostoList.get(position);
        holder.bind(centroCosto);

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
    public int getItemCount(){
        return centroCostoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewId;
        private TextView textViewNombre;
        private Button btnEditar;
        private Button btnEliminar;

        public ViewHolder(View itemView){
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewIdCentroCosto);
            textViewNombre = itemView.findViewById(R.id.textViewNombreCentroCosto);
            btnEditar = itemView.findViewById(R.id.btnEditarElementoCentroCosto);
            btnEliminar = itemView.findViewById(R.id.btnEliminarElementoCentroCosto);
        }

        public void bind(final CentroCosto centroCosto){
            textViewNombre.setText(centroCosto.getNombre());
            textViewId.setText(centroCosto.getId());

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    //Redireccionamiento a EditCentroCostoFragment con valores del registro
                    if (position != RecyclerView.NO_POSITION){
                        CentroCosto centroCostoToEdit = centroCostoList.get(position);

                        //Redirecciona al fragmento EditCentroCostoFragment con valores de registro
                        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                        EditCentroCostoFragment editCentroCostoFragment = new EditCentroCostoFragment();

                        //Envía los valores del registro al fragmento
                        Bundle bundle = new Bundle();
                        bundle.putString("id", centroCostoToEdit.getId());
                        bundle.putString("nombre", centroCostoToEdit.getNombre());
                        editCentroCostoFragment.setArguments(bundle);

                        fragmentManager.beginTransaction()
                                .replace(R.id.container, editCentroCostoFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });
            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    //Loica para eliminar
                    CentroCosto centroCostoToDelete = centroCostoList.get(position);

                    AdminSQLite admin = new AdminSQLite(context);
                    SQLiteDatabase db = admin.getWritableDatabase();

                    String id_to_delete = centroCostoToDelete.getId();

                    //Verificacion si hay registros relacionados (FK)
                    int relatedRecordsCount = 0;
                    Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM OrdenTrabajo WHERE Id_CentroCosto=?",  new String[]{id_to_delete});
                    if(cursor.moveToFirst()){
                        relatedRecordsCount = cursor.getInt(0);
                    }
                    cursor.close();

                    if(relatedRecordsCount > 0){
                        // Hay registros relacionados en otras tablas, muestra un mensaje de error
                        Toast.makeText(context, "Este centro se encuentra en uso, no se puede eliminar", Toast.LENGTH_SHORT).show();
                    } else {
                        // No hay registros relacionados, procede a eliminar el registro principal
                        int changes = db.delete("CentroCosto", "Id_CC=?", new String[]{id_to_delete});
                        db.close();
                        if (changes == 1) {
                            Toast.makeText(context, "Centro eliminado exitosamente", Toast.LENGTH_SHORT).show();
                            centroCostoList.remove(position);
                            notifyItemRemoved(position);
                        } else {
                            Toast.makeText(context, "El Centro no existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}
