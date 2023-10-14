package com.ucsm.proserge.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ucsm.proserge.Epp;
import com.ucsm.proserge.EppAdapter;
import com.ucsm.proserge.R;

import java.util.ArrayList;
import java.util.List;

public class EppsFragment extends Fragment {
    private RecyclerView recyclerView;
    private EppAdapter adapter;
    private List<Epp> eppList;
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

        // Obtén los datos de la base de datos y asigna a eppList
        eppList = getGenericData();
        adapter = new EppAdapter(getContext(), eppList);
        recyclerView.setAdapter(adapter);

        return view;
    }
    private List<Epp> getGenericData() {
        List<Epp> dataList = new ArrayList<>();

        // Agrega algunos valores genéricos a la lista para probar
        dataList.add(new Epp(1, "EPP 1"));
        dataList.add(new Epp(2, "EPP 2"));
        dataList.add(new Epp(3, "EPP 3"));
        dataList.add(new Epp(4, "EPP 4"));
        dataList.add(new Epp(5, "EPP 5"));
        dataList.add(new Epp(6, "EPP 6"));
        dataList.add(new Epp(7, "EPP 7"));
        dataList.add(new Epp(8, "EPP 8"));
        dataList.add(new Epp(9, "EPP 9"));
        dataList.add(new Epp(10, "EPP 10"));

        return dataList;
    }
}
