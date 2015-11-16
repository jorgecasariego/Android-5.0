package com.enterprise.alertdialogmaterial;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener{

    Button mostrarPicker;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        mostrarPicker = (Button) findViewById(R.id.mostrar_picker);
        mostrarPicker.setOnClickListener(this);
        resultado = (TextView) findViewById(R.id.resultado);
    }

    @Override
    public void onClick(View v) {
        mostrarPickerDialog();
    }

    private void mostrarPickerDialog() {
        DialogFragment hora = new TimePickerFragment();
        hora.show(getFragmentManager(), "timePicker");
    }


    /*
    Esta funcion es llamada una vez elegida una hora
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        resultado.setText(hourOfDay + " : " + minute);
    }
}
