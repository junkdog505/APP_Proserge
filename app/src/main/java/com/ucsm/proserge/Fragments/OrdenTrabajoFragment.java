package com.ucsm.proserge.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ucsm.proserge.Adapters.OrdenTrabajoAdapter;
import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.Clases.OrdenTrabajo;
import com.ucsm.proserge.R;

import java.util.ArrayList;
import java.util.List;

public class OrdenTrabajoFragment extends Fragment {
    private RecyclerView recyclerView;
    private OrdenTrabajoAdapter adapter;
    private List<OrdenTrabajo> ordenTrabajoList;
    private TextInputEditText editTextSearchOrdenId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.ordentrabajo_fragment,container,false);

        FloatingActionButton btnAddOrden = view.findViewById(R.id.floatingbuttonaddorden);

        btnAddOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Redireccion a fragment AddOrdenFragment
                //Implementar
            }
        });

        recyclerView = view.findViewById(R.id.orden_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Busqueda por ****
        editTextSearchOrdenId = view.findViewById(R.id.editText_searchorden);
        editTextSearchOrdenId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterTrabajadores(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Obtener datos de bd y asignarlos a la lista
        ordenTrabajoList = getOrdenFromDatabase();
        adapter = new OrdenTrabajoAdapter(getContext(),ordenTrabajoList);
        recyclerView.setAdapter(adapter);


        return view;
    }
    private void filterTrabajadores(String text){
        //Implementar
    }

    private List<OrdenTrabajo> getOrdenFromDatabase(){
        List<OrdenTrabajo> ordenTrabajoList = new ArrayList<>();
        AdminSQLite admin = new AdminSQLite(requireContext());
        SQLiteDatabase db = admin.getReadableDatabase();

        //Consulta en bd para obtener registros
        Cursor cursor = db.rawQuery("SELECT * FROM OrdenTrabajo GROUP BY Id_ot", null);

        if(cursor.moveToFirst()){
            do{
                String id_OT = cursor.getString(0);
                String fecha = cursor.getString(1);
                String nombre = cursor.getString(2);
                String id_trabajador = cursor.getString(3);
                String id_epps = cursor.getString(4);
                String id_centrocosto = cursor.getString(5);
                String tipoentrega = cursor.getString(6);

                //Crear objeto Orden y agregarlo a la lista
                OrdenTrabajo ordenTrabajo = new OrdenTrabajo(id_OT,fecha,nombre,id_trabajador,id_epps,id_centrocosto,tipoentrega);
                ordenTrabajoList.add(ordenTrabajo);
            }while(cursor.moveToNext());
        }
        //Cierre cursor y bd
        cursor.close();
        db.close();

        return ordenTrabajoList;
    }
}