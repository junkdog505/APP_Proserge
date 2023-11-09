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

import org.w3c.dom.Text;

public class AddTrabajadorFragment extends Fragment {
    String dni, nombres, apellidos, cargo, contrato;
    TextInputEditText editTextDni, editTextNombres, editTextApellidos, editTextCargo, editTextContrato;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.addtrabajador_fragment, container, false);

        editTextDni = view.findViewById(R.id.editText_addtrabajadordni);
        editTextNombres = view.findViewById(R.id.editText_addtrabajadornombres);
        editTextApellidos = view.findViewById(R.id.editText_addtrabajadorapellidos);
        editTextCargo = view.findViewById(R.id.editText_addtrabajadorcargo);
        editTextContrato = view.findViewById(R.id.editText_addtrabajadorcontrato);

        Button btnAgregarTrabajador = view.findViewById(R.id.btnAddTrabajador);
        btnAgregarTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLite admin = new AdminSQLite(requireContext());
                SQLiteDatabase db = admin.getWritableDatabase();

                dni = editTextDni.getText().toString();
                nombres = editTextNombres.getText().toString();
                apellidos = editTextApellidos.getText().toString();
                cargo = editTextCargo.getText().toString();
                contrato = editTextContrato.getText().toString();

                if(!dni.isEmpty() && !nombres.isEmpty() && !apellidos.isEmpty() && !cargo.isEmpty() && !contrato.isEmpty()){
                    ContentValues valores = new ContentValues();
                    valores.put("DNI", dni);
                    valores.put("Nombres", nombres);
                    valores.put("Apellidos", apellidos);
                    valores.put("Cargo", cargo);
                    valores.put("TipoContrato", contrato);

                    //Inserción del registro
                    db.insert("Trabajador", null, valores);
                    db.close();

                    Toast.makeText(getContext(), "Trabajador agregado correctamente", Toast.LENGTH_SHORT).show();
                    getParentFragmentManager().popBackStack();// Cierre del fragmento para regresar al anterior
                }else{
                    Toast.makeText(getContext(), "Datos completos requeridos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
