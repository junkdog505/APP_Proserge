package com.ucsm.proserge.Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ucsm.proserge.CRUD.AddCentroCostoFragment;
import com.ucsm.proserge.R;

public class CentroCostoFragment extends Fragment {
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

        return view;
    }
}
