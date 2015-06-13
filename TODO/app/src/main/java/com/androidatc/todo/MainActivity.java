package com.androidatc.todo;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemLongClickListener{

    //Paso 1: Declarar las instancias de las listas y el adaptador
    ListView lista;

    //Al declarar el adaptador describimos el tipo de objetos que manejara internamente
    TareaArrayAdapter adaptador;

    ArrayList<Tarea> tareas = new ArrayList<Tarea>();

    //Tarea 2: Agregar vistas para agregar nuevas tareas
    EditText nuevaTarea;
    Button agregar;
    Button horaElegida;
    int hora = -1;
    int minuto = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Paso 2: Obtener una referencia a la lista
        lista = (ListView)findViewById(R.id.lista);

        //Paso 8.2: Crear metodo para crear tareas estaticamente
        crearTareas();

        //Paso 3: Inicializar el adaptador
        adaptador = new TareaArrayAdapter(this, tareas);

        //Paso 4: Relacionar el adaptador con la lista
        lista.setAdapter(adaptador);

        //Paso 5: Crear nuestro propio origen de datos. Crear una clase (Tarea) que represente cada item de la lista
        //Paso 6: El layout usado solo tiene un TextView. Crear nuestro propio Adapter

        //Agregando nuevas vistas
        nuevaTarea = (EditText)findViewById(R.id.nuevaTareaEditText);

        agregar = (Button)findViewById(R.id.agregarTareaButton);
        agregar.setOnClickListener(this);

        horaElegida = (Button)findViewById(R.id.horaElegidaButton);
        horaElegida.setOnClickListener(this);

        lista.setOnItemLongClickListener(this);


    }

    /**
     * Creamos tareas estaticas para comenzar
     */
    public void crearTareas(){
        tareas.add(new Tarea("Ir al gym", "18:00", R.drawable.ic_note));
        tareas.add(new Tarea("Estudiar Android", "8:00", R.drawable.ic_note));
        tareas.add(new Tarea("LLamar a asistente", "11:00", R.drawable.ic_note));
    }


    @Override
    public void onClick(View v) {
        String nuevo = nuevaTarea.getText().toString();

        switch (v.getId()){
            case R.id.agregarTareaButton:

                //Controlamos que haya ingresado en tarea y elegido hora y minuto
                if(nuevo.length() > 0 && hora != -1 && minuto != -1){
                    //Agregamos la nueva tarea
                    agregarNuevaTarea(nuevo);
                    //Actualizamos la lista
                    adaptador.notifyDataSetChanged();
                    //Limpiamos el edittext para la nueva tarea
                    nuevaTarea.setText("");
                } else{
                    Toast.makeText(this, "Es necesario completar todos los campos!!", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.horaElegidaButton:
                showTimePickerDialog(v);
                break;
        }

    }

    public void agregarNuevaTarea(String tarea){
        Log.d("Tarea nueva", "Tarea: " + tarea + " hora: " + hora + " minuto: " + minuto);
        tareas.add(new Tarea(tarea, hora + ":" + minuto, R.drawable.ic_note));
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
        horaElegida.setText(hora + ":" + minuto);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        //Borramos la tarea de la lista
        tareas.remove(position);

        //Actualizamos la lista
        adaptador.notifyDataSetChanged();

        return true;

    }
}
