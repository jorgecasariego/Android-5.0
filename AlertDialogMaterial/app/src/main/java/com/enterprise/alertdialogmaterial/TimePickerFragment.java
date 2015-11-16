package com.enterprise.alertdialogmaterial;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by jorgecasariego on 14/11/15.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private Activity activity;
    private TimePickerDialog.OnTimeSetListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = activity;

        try{
            listener = (TimePickerDialog.OnTimeSetListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " debe implementar OnTimeSetListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Usamos la hora actual para mostrar en el time picker dialog
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);


        // Creamos una nueva instancia de time picker y retornamos
        return new TimePickerDialog(activity,
                listener,
                hora,
                minuto,
                android.text.format.DateFormat.is24HourFormat(activity));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}
