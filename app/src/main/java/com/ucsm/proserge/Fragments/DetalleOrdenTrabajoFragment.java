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

import com.ucsm.proserge.Adapters.DetalleOrdenTrabajoAdapter;
import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.Clases.DetalleOrdenTrabajo;
import com.ucsm.proserge.R;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;

public class DetalleOrdenTrabajoFragment extends Fragment {

    private RecyclerView recyclerView;
    private DetalleOrdenTrabajoAdapter adapter;
    private List<DetalleOrdenTrabajo> detalleOrdenTrabajoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalleordentrabajo_fragment,container,false);

        recyclerView = view.findViewById(R.id.ordendetalle_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Obtener datos de BD y asignarlos a la lista (epps)
        detalleOrdenTrabajoList = getDetalleFromDatabase();
        adapter = new DetalleOrdenTrabajoAdapter(getContext(), detalleOrdenTrabajoList);
        recyclerView.setAdapter(adapter);

        String id_ot, fecha;

        return view;
    }

    private List<DetalleOrdenTrabajo> getDetalleFromDatabase(){
        String id_ot;
        id_ot = getArguments().getString("id");

        List<DetalleOrdenTrabajo> detalleOrdenTrabajoList = new ArrayList<>();
        AdminSQLite admin = new AdminSQLite(requireContext());
        SQLiteDatabase db = admin.getReadableDatabase();

        //Consulta en BD para obtencion de registros
        Cursor cursor = db.rawQuery("SELECT EPPS.Nombre FROM OrdenTrabajo INNER JOIN EPPS ON OrdenTrabajo.Id_EPPS = EPPS.Id_epp WHERE Id_OT = " + id_ot, null);

        if(cursor.moveToFirst()){
            do{
                String nombre_epp = cursor.getString(0);

                //Crear objeto DetalleOrdenTrabajo.java y agregarlo a la lista
                DetalleOrdenTrabajo detalleOrdenTrabajo = new DetalleOrdenTrabajo(nombre_epp);
                detalleOrdenTrabajoList.add(detalleOrdenTrabajo);
            }while(cursor.moveToNext());
        }
        //Cierre cursor y bd
        cursor.close();
        db.close();

        return detalleOrdenTrabajoList;
    }
}