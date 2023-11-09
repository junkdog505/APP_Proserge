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
import com.ucsm.proserge.Adapters.CentroCostoAdapter;
import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.CRUD.AddCentroCostoFragment;
import com.ucsm.proserge.Clases.CentroCosto;
import com.ucsm.proserge.Clases.Trabajador;
import com.ucsm.proserge.R;

import java.util.ArrayList;
import java.util.List;

public class CentroCostoFragment extends Fragment {
    private RecyclerView recyclerView;
    private CentroCostoAdapter adapter;
    private List<CentroCosto> centroCostoList;
    private TextInputEditText editTextSearchCentroCostoId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.centrocosto_fragment,container,false);

        FloatingActionButton btnAddCentroCosto = view.findViewById(R.id.floatingbuttonaddcentrocosto);
        btnAddCentroCosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Redireción a fragment AddCentroCostoFragment
                Fragment targetFragment = new AddCentroCostoFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, new AddCentroCostoFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        recyclerView = view.findViewById(R.id.centrocosto_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Búsqueda por ID
        editTextSearchCentroCostoId = view.findViewById(R.id.editText_searchcentrocostoid);
        editTextSearchCentroCostoId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCentroCosto(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Obtén los datos de la base de datos y asigna a centroCostoList
        centroCostoList = getCentroCostoFromDatabase();
        adapter = new CentroCostoAdapter(getContext(), centroCostoList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void filterCentroCosto(String text) {
        List<CentroCosto> filteredList = new ArrayList<>();

        for (CentroCosto centroCosto : centroCostoList) {
            // Filtra por el número de id
            if (centroCosto.getId().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(centroCosto);
            }
        }

        // Actualiza la lista del adaptador con los resultados filtrados
        adapter.filterList(filteredList);
    }

    private List<CentroCosto> getCentroCostoFromDatabase(){
        List<CentroCosto> centroCostoList = new ArrayList<>();
        AdminSQLite admin = new AdminSQLite(requireContext());
        SQLiteDatabase db = admin.getReadableDatabase();

        // Realiza una consulta en tu base de datos para obtener los registros
        Cursor cursor = db.rawQuery("select * from CentroCosto", null);

        if (cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);

                //Crea un objeto CentroCosto y agrega a la lista
                CentroCosto centroCosto = new CentroCosto(id, nombre);
                centroCostoList.add(centroCosto);
            }while (cursor.moveToNext());
        }

        //Cierra el cursor y bd
        cursor.close();
        db.close();

        return centroCostoList;
    }
}
