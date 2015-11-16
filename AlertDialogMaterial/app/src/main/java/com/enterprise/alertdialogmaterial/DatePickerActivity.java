package com.enterprise.alertdialogmaterial;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener {

    Button diaButton;
    TextView diaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        diaButton = (Button)findViewById(R.id.diaElegidaButton);
        diaButton.setOnClickListener(this);

        diaTextView = (TextView) findViewById(R.id.diaTextView);
    }

    @Override
    public void onClick(View v) {
        showDatePickerDialog();
    }

    private void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //Actualizamos la nueva hora en el boton
        diaTextView.setText("Año elegido: " + dayOfMonth + " - " + monthOfYear + " - " + year);
    }
}
