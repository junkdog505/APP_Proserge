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
import com.ucsm.proserge.CRUD.EditTrabajadorFragment;
import com.ucsm.proserge.Clases.CentroCosto;
import com.ucsm.proserge.Clases.Trabajador;
import com.ucsm.proserge.R;

import org.w3c.dom.Text;

import java.nio.BufferUnderflowException;
import java.util.List;

public class TrabajadorAdapter extends RecyclerView.Adapter<TrabajadorAdapter.ViewHolder> {
    private List<Trabajador> trabajadorList;
    private Context context;

    public TrabajadorAdapter(Context context, List<Trabajador> trabajadorList){
        this.context = context;
        this.trabajadorList = trabajadorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trabajador, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Trabajador trabajador = trabajadorList.get(position);
        holder.bind(trabajador);
    }

    @Override
    public int getItemCount(){
        return trabajadorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewDni, textViewNombres, textViewApellidos, textViewCargo,textViewContrato;
        private Button btnEditar, btnEliminar;

        public ViewHolder(View itemView){
            super(itemView);
            textViewDni = itemView.findViewById(R.id.textViewDniTrabajador);
            textViewNombres = itemView.findViewById(R.id.textViewNombresTrabajador);
            textViewApellidos = itemView.findViewById(R.id.textViewApellidosTrabajador);
            textViewCargo = itemView.findViewById(R.id.textViewCargoTrabajador);
            textViewContrato = itemView.findViewById(R.id.textViewContratoTrabajador);

            btnEditar = itemView.findViewById(R.id.btnEditarElementoTrabajador);
            btnEliminar = itemView.findViewById(R.id.btnEliminarElementoTrabajador);
        }

        public void bind(final Trabajador trabajador){
            textViewDni.setText(trabajador.getDni());
            textViewNombres.setText(trabajador.getNombres());
            textViewApellidos.setText(trabajador.getApellidos());
            textViewCargo.setText(trabajador.getCargo());
            textViewContrato.setText(trabajador.getTipocontrato());

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    //Redireccionamiento a EditCentroCostoFragment con valores del registro
                    if(position != RecyclerView.NO_POSITION){
                        Trabajador trabajadorToEdit = trabajadorList.get(position);

                        //Redirecciona al fragmento EditCentroCostoFragment con valores de registro
                        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                        EditTrabajadorFragment editTrabajadorFragment = new EditTrabajadorFragment();

                        //Envio de valores de registro al fragmento
                        Bundle bundle = new Bundle();
                        bundle.putString("dni", trabajadorToEdit.getDni());
                        bundle.putString("nombres", trabajadorToEdit.getNombres());
                        bundle.putString("apellidos", trabajadorToEdit.getApellidos());
                        bundle.putString("cargo", trabajadorToEdit.getCargo());
                        bundle.putString("contrato", trabajadorToEdit.getTipocontrato());
                        editTrabajadorFragment.setArguments(bundle);

                        fragmentManager.beginTransaction()
                                .replace(R.id.container, editTrabajadorFragment)
                                .addToBackStack(null)
                                .commit();

                    }
                }
            });

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    //Logica para eliminar
                    Trabajador trabajadorToDelete = trabajadorList.get(position);

                    AdminSQLite admin = new AdminSQLite(context);
                    SQLiteDatabase db = admin.getWritableDatabase();

                    String dni_to_delete = trabajadorToDelete.getDni();

                    //Verificacion si hay registros relacionados (FK)
                    int relatedRecordsCount = 0;
                    Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM OrdenTrabajo WHERE Id_Trabajador=?", new String[]{dni_to_delete});
                    if (cursor.moveToFirst()) {
                        relatedRecordsCount = cursor.getInt(0);
                    }
                    cursor.close();

                    if (relatedRecordsCount > 0) {
                        // Hay registros relacionados en otras tablas, muestra un mensaje de error
                        Toast.makeText(context, "Este trabajador se encuentra en uso, no se puede eliminar", Toast.LENGTH_SHORT).show();
                    } else {
                        // No hay registros relacionados, procede a eliminar el registro principal
                        int changes = db.delete("Trabajador", "DNI=?", new String[]{dni_to_delete});
                        db.close();
                        if (changes == 1) {
                            Toast.makeText(context, "Trabajador eliminado exitosamente", Toast.LENGTH_SHORT).show();
                            trabajadorList.remove(position);
                            notifyItemRemoved(position);
                        } else {
                            Toast.makeText(context, "El Trabajador no existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

    }
}
