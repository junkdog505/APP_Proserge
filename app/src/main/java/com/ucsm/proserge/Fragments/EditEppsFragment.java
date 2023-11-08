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
    String nombre, tipo, clasificacion;
    TextInputEditText editTextNombre, editTextTipo, editTextClasificacion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editepps_fragment,container,false);

        // Recupera los valores del registro desde los argumentos
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
                
            }
        });

        return view;
    }

}
