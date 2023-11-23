package com.ucsm.proserge.CRUD;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.Clases.CentroCosto;
import com.ucsm.proserge.R;

import java.util.ArrayList;
import java.util.List;

public class AddDetalleOrdenFragment extends Fragment {
    private List<CentroCosto> centroCostosList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.addordentrabajo_fragment, container, false);

        Spinner spinner = view.findViewById(R.id.spinner_dropdown);
        TextView textViewAddDetalleCentroCosto = view.findViewById(R.id.textViewAddDetalleCentroCosto);

        centroCostosList = opcCentrosCosto();

        // Crear un adaptador para el Spinner
        ArrayAdapter<CentroCosto> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, centroCostosList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinner.setAdapter(adapter);

        // Manejar la selección del elemento del Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                CentroCosto selectedCentroCosto = centroCostosList.get(position);
                textViewAddDetalleCentroCosto.setText(selectedCentroCosto.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Acción cuando no se selecciona nada
            }
        });

        return view;
    }

    private List<CentroCosto> opcCentrosCosto(){
        List<CentroCosto> optionsList = new ArrayList<>();
        AdminSQLite admin = new AdminSQLite(requireContext());
        SQLiteDatabase db = admin.getReadableDatabase();

        //Consulta en bd para obtencion de opciones
        Cursor cursor = db.rawQuery("SELECT CentroCosto.Id_CC, CentroCosto.Nombre FROM CentroCosto", null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);
                CentroCosto centroCosto = new CentroCosto(id, nombre);
                optionsList.add(centroCosto);
            }while(cursor.moveToNext());
        }
        //Cierres
        cursor.close();
        db.close();

        return optionsList;
    }
}