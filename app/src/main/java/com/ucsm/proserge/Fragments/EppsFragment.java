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
import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.CRUD.AddEppsFragment;
import com.ucsm.proserge.Clases.Epp;
import com.ucsm.proserge.Adapters.EppAdapter;
import com.ucsm.proserge.R;

import java.util.ArrayList;
import java.util.List;

public class EppsFragment extends Fragment {
    private RecyclerView recyclerView;
    private EppAdapter adapter;
    private List<Epp> eppList;
    private TextInputEditText editTextSearchEppNombre;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.epps_fragment,container,false);
        FloatingActionButton btnAddEpps = view.findViewById(R.id.floatingbuttonaddepps);
        btnAddEpps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Redireccion a fragment AddEppsFragment
                Fragment targetFragment = new AddEppsFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, new AddEppsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        recyclerView = view.findViewById(R.id.epps_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Busqueda por Nombre
        editTextSearchEppNombre = view.findViewById(R.id.editText_searcheppnombre);
        editTextSearchEppNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterEpps(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Obtén los datos de la base de datos y asigna a eppList
        eppList = getEppsFromDatabase();
        adapter = new EppAdapter(getContext(), eppList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void filterEpps(String text){
        List<Epp> filteredList = new ArrayList<>();

        for (Epp epp : eppList){
            //filtra por nombre
            if(epp.getNombre().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(epp);
            }
        }
        //Actualiza la lista del adaptador con resultados filtrados
        adapter.filterList(filteredList);
    }

    private List<Epp> getEppsFromDatabase() {
        List<Epp> eppList = new ArrayList<>();
        AdminSQLite admin= new AdminSQLite(requireContext());
        SQLiteDatabase db = admin.getReadableDatabase();

        // Realiza una consulta en tu base de datos para obtener los registros
        Cursor cursor = db.rawQuery("select * from epps", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0); // Suponiendo que el ID está en la primera columna
                String nombre = cursor.getString(1); // Suponiendo que el nombre está en la segunda columna
                String tipo = cursor.getString(2);
                String clasificacion = cursor.getString(3);

                // Crea un objeto Epp y agrégalo a la lista
                Epp epp = new Epp(id, nombre, tipo, clasificacion);
                eppList.add(epp);
            } while (cursor.moveToNext());
        }

        // Cierra el cursor y la base de datos
        cursor.close();
        db.close();

        return eppList;
    }
}
