package com.ucsm.proserge.CRUD;
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
    String nombre, tipo, clasificacion;
    TextInputEditText editTextNombre, editTextTipo, editTextClasificacion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editepps_fragment,container,false);

        // Recupera los valores del registro desde los argumentos
        int id = getArguments().getInt("id");
        String idString = String.valueOf(id); //Pasar el id tipo INT a tipo String para concatenacion posterior
        nombre = getArguments().getString("nombre");
        tipo = getArguments().getString("tipo");
        clasificacion = getArguments().getString("clasificacion");

        // Configura los valores en los campos de entrada de texto
        editTextNombre = view.findViewById(R.id.editText_eppsnombre);
        editTextTipo = view.findViewById(R.id.editText_eppstipo);
        editTextClasificacion = view.findViewById(R.id.editText_eppsclasificacion);

        editTextNombre.setText(nombre);
        editTextTipo.setText(tipo);
        editTextClasificacion.setText(clasificacion);

        Button btnEditarEpp = view.findViewById(R.id.btnEditarEpp);
        btnEditarEpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLite admin= new AdminSQLite(requireContext());
                SQLiteDatabase db = admin.getWritableDatabase();

                String new_nombre = editTextNombre.getText().toString();
                String new_tipo = editTextTipo.getText().toString();
                String new_clasificacion = editTextClasificacion.getText().toString();

                if(!new_nombre.isEmpty()){
                    ContentValues valores = new ContentValues();
                    valores.put("Id_epp", idString);
                    valores.put("Nombre", new_nombre);
                    valores.put("Tipo", new_tipo);
                    valores.put("Clasificacion", new_clasificacion);

                    //Actualización en BD del registro
                    int changes = db.update("EPPS", valores, "Id_epp="+idString, null);
                    db.close();

                    if(changes == 1){
                        Toast.makeText(getContext(), "Edición exitosa", Toast.LENGTH_SHORT).show();
                        getParentFragmentManager().popBackStack();// Cierre del fragmento para regresar al anterior
                    }else{
                        Toast.makeText(getContext(), "Edición fallida", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "El artículo no puede tener un nombre vacío", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}