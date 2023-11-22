package com.ucsm.proserge.CRUD;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.Clases.CentroCosto;
import com.ucsm.proserge.Clases.Epp;
import com.ucsm.proserge.R;

import java.util.ArrayList;
import java.util.List;

public class AddDetalleOrdenFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.addordentrabajo_fragment,container,false);
        // Suponiendo que tienes una lista de opciones obtenidas de la base de datos
        List<String> optionsList = opcCentrosCosto();
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.editText_adddetallecentrocosto);

// Crear un adaptador personalizado
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, optionsList);

        autoCompleteTextView.setAdapter(adapter);
        return view;
    }

    private List<String> opcCentrosCosto(){
        List<String> optionsList = new ArrayList<>();
        AdminSQLite admin = new AdminSQLite(requireContext());
        SQLiteDatabase db = admin.getReadableDatabase();

        //Consulta en bd para obtencion de opciones
        Cursor cursor = db.rawQuery("SELECT CentroCosto.Id_CC, CentroCosto.Nombre FROM CentroCosto", null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);
                CentroCosto centroCosto = new CentroCosto(id, nombre);
                optionsList.add(centroCosto.getNombre());
            }while(cursor.moveToNext());
        }
        //Cierres
        cursor.close();
        db.close();

        return optionsList;
    }

}
