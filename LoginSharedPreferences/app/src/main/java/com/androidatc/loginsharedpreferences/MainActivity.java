package com.androidatc.loginsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nombre;
    private EditText apellido;
    private Button guardar;
    private Button limpiar;

    //1. Creamos las claves a utilizar para guardar las preferencias
    private static final String MIS_PREFERENCIAS = "preferencias";
    private static final String NOMBRE = "nombre";
    private static final String APELLIDO = "apellido";

    //2. Declaramos una variable del tipo SharedPrefences y un editor para manipular los datos
    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //3. Obtenemos una instancia del objeto SharedPrefences pasandole la clave
        preferencias = getSharedPreferences(MIS_PREFERENCIAS, Context.MODE_PRIVATE);
        editor = preferencias.edit();

        nombre = (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.apellido);
        guardar = (Button) findViewById(R.id.guardar);
        limpiar = (Button) findViewById(R.id.limpiar);

        guardar.setOnClickListener(this);
        limpiar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guardar:
                guardarDatos();
                Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();
                break;
            case R.id.limpiar:
                limpiarDatos();
                Toast.makeText(this, "Datos limpiados correctamente", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    //4. Guardamos los datos
    private void guardarDatos() {
        editor.putString(NOMBRE, nombre.getText().toString());
        editor.putString(APELLIDO, apellido.getText().toString());

        editor.commit();
    }

    //6. Cargamos los datos guardados
    private void cargarDatos() {
        preferencias = getSharedPreferences(MIS_PREFERENCIAS, Context.MODE_PRIVATE);

        //7. Recuperamos los valores guardados
        String nombreRecuperado = preferencias.getString(NOMBRE, "");
        String apellidoRecuperado = preferencias.getString(APELLIDO, "");

        nombre.setText(nombreRecuperado);
        apellido.setText(apellidoRecuperado);

    }

    //8. Limpiamos todos los datos del usuario
    private void limpiarDatos() {
        editor.clear();
        editor.commit();

        nombre.setText("");
        apellido.setText("");
    }

    //5. Guardar el estado de una actividad
    //   onCreate -> onStart -> onResume -> onPause -> onStop -> onDestroy
    //   onCreate -> onStart -> onResume -> onPause -> onStop -> onRestart -> onStart -> ....

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        cargarDatos();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        guardarDatos();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }
}
