package com.androidatc.androidui;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * Desplegamos una barra indicando al usuario cuanto tiempo la operación ha progresado
 *
 * Un progressBar tambien puede ser indeterminate el cual muestra una animación ciclica sin una
 * indicación de progreso. Este modo es usado cuando la longitud de la tarea es desconocida
 */
public class ProgressBarActivity extends AppCompatActivity implements View.OnClickListener{

    private ProgressBar firstBar = null;
    private ProgressBar secondBar = null;
    private Button myButton;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        firstBar = (ProgressBar) findViewById(R.id.firstBar);
        secondBar = (ProgressBar) findViewById(R.id.secondBar);

        myButton = (Button)findViewById(R.id.myButton);
        myButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (i == 0 || i == 10) {
            //make the progress bar visible
            firstBar.setVisibility(View.VISIBLE);
            firstBar.setMax(150);
            secondBar.setVisibility(View.VISIBLE);
        } else if (i < firstBar.getMax()) {
            //Set first progress bar value
            firstBar.setProgress(i);
            //Set the second progress bar value
            firstBar.setSecondaryProgress(i + 10);
        } else {

            firstBar.setProgress(0);

            firstBar.setSecondaryProgress(0);

            i = 0;
            firstBar.setVisibility(View.GONE);
            secondBar.setVisibility(View.GONE);
        }
        i = i + 10;
    }
}
