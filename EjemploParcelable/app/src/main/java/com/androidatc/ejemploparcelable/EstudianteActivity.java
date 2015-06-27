package com.androidatc.ejemploparcelable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jorgecasariego on 27/6/15.
 */
public class EstudianteActivity extends Activity {

    TextView nombre;
    TextView edad;
    TextView correo;

    Estudiante e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);

        //Obtenemos los datos del objeto parcelable pasado desde el MainActivity
        e = getIntent().getParcelableExtra("estudiante");

        //Obtenemos las referencias de cada una de las vistas de nuestro layout
        nombre = (TextView) findViewById(R.id.nombre);
        edad = (TextView) findViewById(R.id.edad);
        correo = (TextView) findViewById(R.id.correo);

        if(e != null){
            nombre.setText("Nombre: " + e.nombre);
            edad.setText("Edad: " + e.edad);
            correo.setText("Correo: " + e.correo);
        }else {
            Log.e("EstudianteActivity", "Estudiante es null");
        }


    }
}
