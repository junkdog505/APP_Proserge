package com.ucsm.proserge.Fragments;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ucsm.proserge.Adapters.TrabajadorAdapter;
import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.CRUD.AddTrabajadorFragment;
import com.ucsm.proserge.Clases.Trabajador;
import com.ucsm.proserge.R;

import java.util.ArrayList;
import java.util.List;

public class WorkersFragment extends Fragment {
    private RecyclerView recyclerView;
    private TrabajadorAdapter adapter;
    private List<Trabajador> trabajadorList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workers_fragment,container,false);

        FloatingActionButton btnAddTrabajador = view.findViewById(R.id.floatingbuttonaddtrabajador);
        btnAddTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Redirección a fragment AddTrabajadorFragment
                Fragment targetFragment = new AddTrabajadorFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, new AddTrabajadorFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        recyclerView = view.findViewById(R.id.trabajador_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Obtener los datos de la bd y asignarlos a la lista

        trabajadorList = getTrabajadorFromDatabase();
        adapter = new TrabajadorAdapter(getContext(),trabajadorList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Trabajador> getTrabajadorFromDatabase(){
        List<Trabajador> trabajadorList = new ArrayList<>();
        AdminSQLite admin = new AdminSQLite(requireContext());
        SQLiteDatabase db = admin.getReadableDatabase();

        //Realiza consulta en bd para obtener registros de trabajadores
        Cursor cursor = db.rawQuery("select * from Trabajador", null);

        if(cursor.moveToFirst()){
            do{
                String dni = cursor.getString(0);
                String nombres = cursor.getString(1);
                String apellidos = cursor.getString(2);
                String cargo = cursor.getString(3);
                String contrato = cursor.getString(4);

                //Crear objeto Trabajador y agregarlo a la lista
                Trabajador trabajador = new Trabajador(dni,nombres,apellidos,cargo,contrato);
                trabajadorList.add(trabajador);
            }while(cursor.moveToNext());
        }
        //Cierra el cursor y bd
        cursor.close();
        db.close();

        return trabajadorList;
    }
}
