package com.ucsm.proserge.CRUD;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ucsm.proserge.AdminSQLite;
import com.ucsm.proserge.Clases.CentroCosto;
import com.ucsm.proserge.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddDetalleOrdenFragment extends Fragment {
    private List<CentroCosto> centroCostosList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.addordentrabajo_fragment, container, false);

        // ================================== Input Fecha ==================================
        EditText editTextFecha = view.findViewById(R.id.editText_AddDetalleFecha);
        editTextFecha.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view1, year1, monthOfYear, dayOfMonth) -> {
                calendar.set(Calendar.YEAR, year1);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                editTextFecha.setText(dateFormat.format(calendar.getTime()));
            }, year, month, day);
            datePickerDialog.show();
        });

        // ================================== Input Centro de Costo ==================================
        Spinner spinner = view.findViewById(R.id.spinner_dropdown);
        TextView textViewAddDetalleCentroCosto = view.findViewById(R.id.editText_AddDetalleCentroCosto);

        centroCostosList = opcCentrosCosto();

        // Crear un adaptador para el Spinner
        ArrayAdapter<CentroCosto> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, centroCostosList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinner.setAdapter(adapter);

        // Manejar la selección del elemento del Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                CentroCosto selectedCentroCosto = centroCostosList.get(position);
                textViewAddDetalleCentroCosto.setText(selectedCentroCosto.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Acción cuando no se selecciona nada
            }
        });

        /* preview Trabajador info */
        EditText editTextDni = view.findViewById(R.id.editText_adddetalledni);
        TextView textViewNombres = view.findViewById(R.id.textViewAddDetalleNombresTrabajador);
        TextView textViewApellidos = view.findViewById(R.id.textViewAddDetalleApellidosTrabajador);
        TextView textViewCargo = view.findViewById(R.id.textViewAddDetalleCargoTrabajador);

        // TextChangedListener para capturar los cambios en el DNI
        editTextDni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Método antes de que cambie el texto
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Método cuando el texto cambia
                String dni = charSequence.toString();

                // Aquí puedes llamar a un método para obtener la información asociada al DNI
                obtenerInformacionPorDNI(dni, textViewNombres, textViewApellidos, textViewCargo);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Método después de que cambie el texto
            }
        });
        return view;
    }

    private List<CentroCosto> opcCentrosCosto(){
        List<CentroCosto> optionsList = new ArrayList<>();
        AdminSQLite admin = new AdminSQLite(requireContext());
        SQLiteDatabase db = admin.getReadableDatabase();

        //Consulta en bd para obtencion de opciones
        Cursor cursor = db.rawQuery("SELECT CentroCosto.Id_CC, CentroCosto.Nombre FROM CentroCosto", null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String nombre = cursor.getString(1);
                CentroCosto centroCosto = new CentroCosto(id, nombre);
                optionsList.add(centroCosto);
            }while(cursor.moveToNext());
        }
        //Cierres
        cursor.close();
        db.close();

        return optionsList;
    }

    private void obtenerInformacionPorDNI(String dni, TextView textViewNombres, TextView textViewApellidos, TextView textViewCargo) {
        AdminSQLite admin = new AdminSQLite(requireContext());
        SQLiteDatabase db = admin.getReadableDatabase();

        if(dni.length() == 8){
            //Consulta a BD utilizando un parámetro en la consulta
            Cursor cursor = db.rawQuery("SELECT Trabajador.DNI, Trabajador.Nombres, Trabajador.Apellidos, Trabajador.Cargo FROM Trabajador WHERE DNI = ?", new String[]{dni});
            if(cursor.moveToFirst()){
                textViewNombres.setText(cursor.getString(1));
                textViewApellidos.setText(cursor.getString(2));
                textViewCargo.setText(cursor.getString(3));
            }else{
                textViewNombres.setText("");
                textViewApellidos.setText("Trabajador no encontrado");
                textViewCargo.setText("");
            }
            cursor.close(); //Cerrar cursor después de usarlo
            db.close(); //Cerrar la base de datos después de usarla
        }else{
            textViewNombres.setText("");
            textViewApellidos.setText("DNI Ingresado no válido");
            textViewCargo.setText("");
        }
    }
}