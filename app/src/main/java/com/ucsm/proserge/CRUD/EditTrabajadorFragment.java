package com.ucsm.proserge.CRUD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.R;


public class EditTrabajadorFragment extends Fragment{
    String dnitoedit, nombres, apellidos, cargo, contrato;
    TextInputEditText editTextDni, editTextNombres, editTextApellidos, editTextCargo, editTextContrato;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.edittrabajador_fragment,container,false);

        //Recupera los valores del registro desde los argumentos
        dnitoedit = getArguments().getString("dni");
        nombres = getArguments().getString("nombres");
        apellidos = getArguments().getString("apellidos");
        cargo = getArguments().getString("cargo");
        contrato = getArguments().getString("contrato");

        //Configura los valores en los campos de entrada de texto
        editTextNombres = view.findViewById(R.id.editText_edittrabajadornombres);
        editTextApellidos = view.findViewById(R.id.editText_edittrabajadorapellidos);
        editTextCargo = view.findViewById(R.id.editText_edittrabajadorcargo);
        editTextContrato = view.findViewById(R.id.editText_edittrabajadorcontrato);

        editTextNombres.setText(nombres);
        editTextApellidos.setText(apellidos);
        editTextCargo.setText(cargo);
        editTextContrato.setText(contrato);

        Button btnEditarTrabajador = view.findViewById(R.id.btnEditarTrabajador);
        btnEditarTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLite admin = new AdminSQLite(requireContext());
                SQLiteDatabase db = admin.getWritableDatabase();

                String new_nombres = editTextNombres.getText().toString();
                String new_apellidos = editTextApellidos.getText().toString();
                String new_cargo = editTextCargo.getText().toString();
                String new_contrato = editTextContrato.getText().toString();

                if(!new_nombres.isEmpty() && !new_apellidos.isEmpty() && !new_cargo.isEmpty() && !new_contrato.isEmpty()){
                    ContentValues valores = new ContentValues();
                    valores.put("DNI", dnitoedit);
                    valores.put("Nombres", new_nombres);
                    valores.put("Apellidos", new_apellidos);
                    valores.put("Cargo", new_cargo);
                    valores.put("TipoContrato", new_contrato);

                    //Actualización en BD del registro
                    int changes = db.update("Trabajador", valores, "DNI="+dnitoedit,null);
                    db.close();

                    if(changes==1){
                        Toast.makeText(getContext(), "Edición exitosa", Toast.LENGTH_SHORT).show();
                        getParentFragmentManager().popBackStack();// Cierre del fragmento para regresar al anterior
                    }else{
                        Toast.makeText(getContext(), "Edición fallida", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }
}
