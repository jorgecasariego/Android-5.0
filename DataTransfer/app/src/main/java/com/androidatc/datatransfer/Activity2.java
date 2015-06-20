package com.androidatc.datatransfer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class Activity2 extends ActionBarActivity {

    TextView nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        nombreUsuario = (TextView) findViewById(R.id.nombreTextView);

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }

        //Obtenemos los datos via la CLAVE
        String nombreTexto = extras.getString("NOMBRE");
        if(nombreTexto != null){
            nombreUsuario.setText(nombreTexto);
        }
    }

}
