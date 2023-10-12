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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ucsm.proserge.R;

public class EppsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.epps_fragment,container,false);

        FloatingActionButton btnAddEpps = view.findViewById(R.id.floatingbuttonaddepps);
        btnAddEpps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment homeFragment = new HomeFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, new HomeFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });


        return view;

    }
}
