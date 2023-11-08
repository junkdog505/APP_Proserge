package com.ucsm.proserge.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
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

        return view;


    }
}
