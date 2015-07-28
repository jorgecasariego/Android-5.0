package com.androidatc.pantallapreferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt1, bt2;
    private TextView nombre, edad, sexo, sonido;
    private String nom, se;
    private boolean ed, so;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button)findViewById(R.id.button1);
        bt1.setOnClickListener(this);

        bt2 = (Button)findViewById(R.id.button2);
        bt2.setOnClickListener(this);

        nombre = (TextView)findViewById(R.id.nombre);
        edad = (TextView)findViewById(R.id.edad);
        sexo = (TextView)findViewById(R.id.sexo);
        sonido = (TextView)findViewById(R.id.sonido);
        consultaDatos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, MisPreferencias.class));

        return super.onOptionsItemSelected(item);
    }

    public void consultaDatos(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        nom = pref.getString("nombre", "");
        se = pref.getString("sexo", "");
        ed = pref.getBoolean("edad", false);
        so = pref.getBoolean("sonido", false);
        nombre.setText("Nombre :"+nom);
        sexo.setText("Sexo: "+se);
        edad.setText("Edad: "+String.valueOf(ed));
        sonido.setText("Sonido: " + String.valueOf(so));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, MisPreferencias.class));
                break;
            case R.id.button2:
                consultaDatos();
                break;
        }
    }
}
