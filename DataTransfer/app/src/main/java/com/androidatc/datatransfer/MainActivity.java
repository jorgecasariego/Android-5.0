package com.androidatc.datatransfer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nombreIngresado;
    Button loginButton;
    Button registrarseButton;

    final static int SHOW_REGISTRO = 1;

    String nombreUsuarioNuevo = "";

    final static String NOMBRE_REGISTRO = "nombre_registro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreIngresado = (EditText) findViewById(R.id.nombreEditText);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        registrarseButton = (Button) findViewById(R.id.registrarseButton);
        registrarseButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.loginButton:
                String nombre = nombreIngresado.getText().toString();

                if(nombre.equals(nombreUsuarioNuevo) ){
                    Intent intent = new Intent(this, Activity2.class);
                    intent.putExtra("NOMBRE", nombre);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Nombre de usuario incorrecto", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.registrarseButton:
                Intent i = new Intent(this, Registrarse.class);
                startActivityForResult(i, SHOW_REGISTRO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.v("onActivityResult", "Request Code: " + requestCode + "Result code: " + resultCode);
        switch (requestCode){
            case SHOW_REGISTRO:
                if(resultCode == RESULT_OK) {
                    Bundle resultado = data.getExtras();
                    nombreUsuarioNuevo = resultado.getString(NOMBRE_REGISTRO);
                    Log.v("Nombre usuario:",  nombreUsuarioNuevo);
                }
                break;
        }
    }
}
