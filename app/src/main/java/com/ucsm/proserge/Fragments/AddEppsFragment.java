package com.ucsm.proserge.Fragments;
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

public class AddEppsFragment extends Fragment{
    String nombre, tipo, clasificacion;
    TextInputEditText editTextNombre, editTextTipo, editTextClasificacion;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addepps_fragment,container,false);

        editTextNombre = view.findViewById(R.id.editText_addeppsnombre);
        editTextTipo = view.findViewById(R.id.editText_addeppstipo);
        editTextClasificacion = view.findViewById(R.id.editText_addeppsclasificacion);

        Button btnRegistrarEpp = view.findViewById(R.id.btnRegistrarEpp);
        btnRegistrarEpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLite admin= new AdminSQLite(requireContext());
                SQLiteDatabase db = admin.getWritableDatabase();

                nombre = editTextNombre.getText().toString();
                tipo = editTextTipo.getText().toString();
                clasificacion = editTextClasificacion.getText().toString();


                if(!nombre.isEmpty()){
                    ContentValues valores = new ContentValues();
                    valores.put("Nombre", nombre);
                    valores.put("Tipo", tipo);
                    valores.put("Clasificacion", clasificacion);

                    //Insercion del registro
                    db.insert("EPPS", null, valores);
                    db.close();

                    Toast.makeText(getContext(), "Artículo agregado correctamente", Toast.LENGTH_SHORT).show();
                    getParentFragmentManager().popBackStack();// Cierre del fragmento para regresar al anterior
                }else{
                    Toast.makeText(getContext(), "El artículo no puede tener un nombre vacío", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}