package com.ucsm.proserge.CRUD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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

import com.google.android.material.textfield.TextInputEditText;
import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.R;

public class EditCentroCostoFragment extends Fragment {
    String id, nombre;
    TextInputEditText editTextId, editTextNombre;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.editcentrocosto_fragment,container,false);

        //Recupera los valores del registro desde los argumentos
        id = getArguments().getString("id");
        nombre = getArguments().getString("nombre");

        //Configura los valores en los campos de entrada de texto
        editTextId = view.findViewById(R.id.editText_editcentrocostoid);
        editTextNombre = view.findViewById(R.id.editText_editcentrocostonombre);

        editTextId.setText(id);
        editTextNombre.setText(nombre);

        Button btnEditarCentroCosto = view.findViewById(R.id.btnEditarCentroCosto);
        btnEditarCentroCosto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AdminSQLite admin = new AdminSQLite(requireContext());
                SQLiteDatabase db = admin.getWritableDatabase();

                String new_id = editTextId.getText().toString();
                String new_nombre = editTextNombre.getText().toString();

                if(!new_id.isEmpty() && !new_nombre.isEmpty()){
                    ContentValues valores = new ContentValues();
                    valores.put("Id_CC", new_id);
                    valores.put("Nombre", new_nombre);

                    //Acualización en BD del registro
                    int changes = db.update("CentroCosto", valores, "Id_CC"+id, null);
                    db.close();

                    if(changes == 1){
                        Toast.makeText(getContext(), "Edición exitosa", Toast.LENGTH_SHORT).show();
                        getParentFragmentManager().popBackStack();// Cierre del fragmento para regresar al anterior
                    }else{
                        Toast.makeText(getContext(), "Edición fallida", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}
