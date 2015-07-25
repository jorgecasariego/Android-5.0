package com.androidatc.dailyfortune;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidatc.dailyfortune.R;

public class MainActivity extends ActionBarActivity {

    Button comenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyPreferences pref = new MyPreferences(MainActivity.this);

        //Primero chequeamos si el usuario ha ingresado un nombre antes
        //o es la primera vez que abre la aplicación
        if (!pref.isFirstTime()) {
            Intent i = new Intent(getApplicationContext(), FortuneActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            finish();
        } else {
            comenzar = (Button) findViewById(R.id.comenzar);
            comenzar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    SaveUserName(v);
                }
            });
        }
    }

    public void SaveUserName(View v) {
        EditText userName = (EditText)findViewById(R.id.etNombre);

        MyPreferences pref = new MyPreferences(MainActivity.this);
        pref.setUsername(userName.getText().toString());

        Intent i = new Intent(getApplicationContext(), FortuneActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
    }


}
