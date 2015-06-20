package com.androidatc.explicitintents;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Activity1 extends AppCompatActivity implements View.OnClickListener{

    Button abrirActividad2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);

        abrirActividad2Button = (Button) findViewById(R.id.activity2Button);
        abrirActividad2Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Activity2.class);
        startActivity(i);
    }
}
