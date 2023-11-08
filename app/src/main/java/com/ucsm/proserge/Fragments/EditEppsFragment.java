package com.ucsm.proserge.Fragments;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.R;

public class EditEppsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editepps_fragment,container,false);

        // Recupera los valores del registro desde los argumentos
        String nombre = getArguments().getString("nombre");
        String tipo = getArguments().getString("tipo");
        String clasificacion = getArguments().getString("clasificacion");

        // Configura los valores en los campos de entrada de texto
        TextInputEditText editTextNombre = view.findViewById(R.id.editText_eppsnombre);
        TextInputEditText editTextTipo = view.findViewById(R.id.editText_eppstipo);
        TextInputEditText editTextClasificacion = view.findViewById(R.id.editText_eppsclasificacion);

        editTextNombre.setText(nombre);
        editTextTipo.setText(tipo);
        editTextClasificacion.setText(clasificacion);

        Button btnEditarEpp = view.findViewById(R.id.btnEditarEpp);
        AdminSQLite admin= new AdminSQLite(requireContext());
        SQLiteDatabase db = admin.getWritableDatabase();

        btnEditarEpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Nuevos valores en los EditText
                String Nombre = editTextNombre.getText().toString();
                String Tipo = editTextTipo.getText().toString();
                String Clasificacion = editTextClasificacion.getText().toString();

                // ID del registro a actualizar
                int idRegistroInt = getArguments().getInt("id");
                String idRegistro = String.valueOf(idRegistroInt);

                // Actualiza el registro en la base de datos
                ContentValues valores = new ContentValues();
                valores.put("Nombre", "ASD");
                valores.put("Tipo", "asd");
                valores.put("Clasificacion", "asds");

                String whereClause = "Id_epp = ?";
                String[] whereArgs = {String.valueOf(idRegistro)};

                int filasActualizadas = db.update("EPPS", valores, whereClause, whereArgs);

                if (filasActualizadas > 0) {
                    // La actualización se realizó con éxito
                    Toast.makeText(getContext(), "Actualización exitosa", Toast.LENGTH_SHORT).show();

                    // Notifica al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();

                    // Vuelve al fragmento EppsFragment
                    FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                    ft.replace(R.id.container, new EppsFragment());
                    ft.addToBackStack(null);
                    ft.commit();

                } else {
                    // No se encontraron registros para actualizar o la actualización falló
                    Toast.makeText(getContext(), "La actualización no se realizó", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}
