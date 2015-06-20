package com.androidatc.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button paginaWeb;
    Button llamar;
    Button camara;
    Button googleMaps;
    Button enviarCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paginaWeb = (Button) findViewById(R.id.abrirPaginaWebButton);
        paginaWeb.setOnClickListener(this);

        llamar = (Button) findViewById(R.id.llamarButton);
        llamar.setOnClickListener(this);

        camara = (Button) findViewById(R.id.camaraButton);
        camara.setOnClickListener(this);

        googleMaps = (Button) findViewById(R.id.abrirGoogleMaps);
        googleMaps.setOnClickListener(this);

        enviarCorreo = (Button) findViewById(R.id.enviarCorreoButton);
        enviarCorreo.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.abrirPaginaWebButton:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com.py"));
                startActivity(intent);
                break;
            case R.id.llamarButton:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0981442624"));
                startActivity(intent);
                break;
            case R.id.camaraButton:
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
                break;
            case R.id.abrirGoogleMaps:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-25.325278, -57.637248"));

                //Para hacer mas seguro podemos verificar que el intent pueda ser resuelto por al menos una actividad
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                } else{
                    Log.e("Error", "Ninguna aplicación puede ejecutar este intent");
                }

                break;
            case R.id.enviarCorreoButton:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
                intent.putExtra(Intent.EXTRA_TEXT, "Texto del correo");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"jorgekasa@gmail.com"});

                startActivity(intent);
                break;

        }
    }
}
