package com.androidatc.ejemploparcelable;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText nombre;
    EditText edad;
    EditText correo;
    Button registrarse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos las referencias de cada una de las vistas de nuestro layout
        nombre = (EditText) findViewById(R.id.nombre);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        registrarse = (Button) findViewById(R.id.registrarse);
        registrarse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // Creamos una instancia de la clase Estudiante con los datos ingresados
        Estudiante estudiante= new Estudiante(nombre.getText().toString(),
                Integer.parseInt(edad.getText().toString()),
                correo.getText().toString());

        // Creamos un intent
        Intent intent = new Intent(this, EstudianteActivity.class);

        // Le pasamos el objeto estudiante como parcelable a EstudianteAcitivy
        intent.putExtra("estudiante",estudiante);

        // Opening the activity
        startActivity(intent);
    }
}
