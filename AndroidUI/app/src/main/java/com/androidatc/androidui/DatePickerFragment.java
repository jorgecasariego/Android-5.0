package com.androidatc.androidui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by jorgecasariego on 17/7/15.
 *
 * La clase DatePickerDialog define el estilo y la estructura del dialogo, y
 * la clase DialogFragment será usada como contenedor del dialogo. Esta clase
 * proporciona todos los controles que necesitamos para crear un Dialogo y modificar
 * su apariencia
 *
 * Usar la clase DialogFragment nos asegura la correcta gestión de los eventos
 * que puedan ocurrir
 */
public class DatePickerFragment extends DialogFragment implements OnDateSetListener{

    private Activity mActivity;
    private DatePickerDialog.OnDateSetListener mListener;


    // A continuación sobrescribimos el método onAttach() para obligar que la actividad sobrescriba
    // el interfaz OnDateSetListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = activity;

        try {
            mListener = (DatePickerDialog.OnDateSetListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnDateSetListener");
        }
    }

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar calendar = Calendar.getInstance();
        int year= calendar.get(calendar.YEAR);
        int month=calendar.get(calendar.MONTH);
        int day=calendar.get(calendar.DAY_OF_MONTH);



        // Create a new instance of TimePickerDialog and return it
        return new DatePickerDialog(getActivity(), mListener, year, month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }
}
