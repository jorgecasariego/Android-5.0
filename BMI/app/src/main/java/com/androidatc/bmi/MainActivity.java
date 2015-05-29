package com.androidatc.bmi;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private float altura;
    private float peso;
    private float BMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos vistas
        final Button generarBMI = (Button)findViewById(R.id.generarButton);
        final EditText altura_value = (EditText)findViewById(R.id.alturaId);
        final EditText peso_value = (EditText)findViewById(R.id.pesoId);
        final TextView resultado = (TextView)findViewById(R.id.resultadoTextView);

        generarBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(altura_value.getText().length() > 0 && peso_value.getText().length() > 0) {
                    altura = Float.parseFloat(altura_value.getText().toString());
                    peso = Float.parseFloat(peso_value.getText().toString());

                    BMI = calcularBMI(altura, peso);

                    if(BMI < 16){
                        resultado.setText("Tu BMI: " + BMI + " (es muy inferior al normal)");
                    } else if(BMI < 18.5){
                        resultado.setText("Tu BMI: " + BMI + " (es bajo peso)");
                    } else if(BMI < 25){
                        resultado.setText("Tu BMI: " + BMI + " (es normal)");
                    } else if(BMI < 30){
                        resultado.setText("Tu BMI: " + BMI + " (es sobrepeso)");
                    } else {
                        resultado.setText("Tu BMI: " + BMI + " (es obeso)");
                    }
                }

            }
        });

    }

    private float calcularBMI(float altura, float peso) {
        altura = (altura / 100);

        return (float) (peso / (altura * altura));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
