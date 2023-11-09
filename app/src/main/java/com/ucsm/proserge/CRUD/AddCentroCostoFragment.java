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

import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.R;

import com.google.android.material.textfield.TextInputEditText;

public class AddCentroCostoFragment extends Fragment {
    String id, nombre;
    TextInputEditText editTextId, editTextNombre;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.addcentrocosto_fragment, container, false);

        editTextId = view.findViewById(R.id.editText_addcentrocostoid);
        editTextNombre = view.findViewById(R.id.editText_addcentrocostonombre);

        Button btnRegistrarCentroCosto = view.findViewById(R.id.btnRegistrarCentroCosto);
        btnRegistrarCentroCosto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AdminSQLite admin= new AdminSQLite(requireContext());
                SQLiteDatabase db = admin.getWritableDatabase();

                id = editTextId.getText().toString();
                nombre = editTextNombre.getText().toString();

                if(!id.isEmpty() && !nombre.isEmpty()){
                    ContentValues valores = new ContentValues();
                    valores.put("Id_CC", id);
                    valores.put("Nombre", nombre);

                    //Inserción del registro
                    db.insert("CentroCosto", null, valores);
                    db.close();

                    Toast.makeText(getContext(), "Centro de Costo agregado correctamente", Toast.LENGTH_SHORT).show();
                    getParentFragmentManager().popBackStack();// Cierre del fragmento para regresar al anterior
                }else{
                    Toast.makeText(getContext(), "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}