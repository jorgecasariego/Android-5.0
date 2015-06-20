package com.androidatc.datatransfer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Registrarse extends ActionBarActivity implements View.OnClickListener{

    final static String NOMBRE_REGISTRO = "nombre_registro";

    Button registrarse;
    EditText nombreIngresado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        nombreIngresado = (EditText) findViewById(R.id.nombreEditText);

        registrarse = (Button) findViewById(R.id.registrarseButton);
        registrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void finish() {
        //Preparar el intent
        Intent datos = new Intent();
        datos.putExtra(NOMBRE_REGISTRO, nombreIngresado.getText().toString());

        //Enviamos a la actividad que nos llamo ok y retornamos el dato
        setResult(RESULT_OK, datos);


        super.finish();
    }
}
