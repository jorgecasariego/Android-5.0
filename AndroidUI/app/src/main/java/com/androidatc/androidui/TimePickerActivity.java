package com.androidatc.androidui;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


public class TimePickerActivity extends AppCompatActivity implements
        View.OnClickListener,
        TimePickerDialog.OnTimeSetListener {

    Button horaElegidaButton;
    TextView horaElegidaTextView;
    int hora = -1;
    int minuto = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        horaElegidaButton = (Button)findViewById(R.id.horaElegidaButton);
        horaElegidaButton.setOnClickListener(this);

        horaElegidaTextView = (TextView) findViewById(R.id.horaTareaTextView);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.horaElegidaButton:
                showTimePickerDialog(v);
                break;
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");

    }

    /**
     * Una vez elegida una hora se llama a este metodo
     * @param view
     * @param hourOfDay
     * @param minute
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hora = hourOfDay;
        minuto = minute;

        //Actualizamos la nueva hora en el boton
        horaElegidaTextView.setText(hora + ":" + minuto);

    }
}
