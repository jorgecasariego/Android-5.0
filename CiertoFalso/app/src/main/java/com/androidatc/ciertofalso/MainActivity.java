package com.androidatc.ciertofalso;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    //Creamos coleccion para guardar las preguntas
    HashMap<String, Boolean> preguntas;

    //Creamos variable para guardar la respuesta corecta actual
    Boolean respuestaCorrecta;

    Integer puntaje = 0;

    Button cierto;
    Button falso;
    TextView preguntaTextView;
    EditText puntajeAcumulado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cierto = (Button)findViewById(R.id.ciertoButton);
        falso = (Button)findViewById(R.id.falsoButton);
        preguntaTextView = (TextView)findViewById(R.id.preguntaId);
        puntajeAcumulado = (EditText)findViewById(R.id.puntajeId);

        puntajeAcumulado.setText(puntaje.toString());

        generarPreguntas();
        obtenerPreguntaRandom();


        cierto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarRespuesta(true)){
                    AlertDialog alerta = new AlertDialog.Builder(MainActivity.this).create();
                    alerta.setTitle("Respuesta");
                    alerta.setMessage("Respuesta correcta!!!");
                    alerta.show();

                    puntaje = puntaje + 5;
                    puntajeAcumulado.setText(puntaje.toString());

                    obtenerPreguntaRandom();
                } else{
                    AlertDialog alerta = new AlertDialog.Builder(MainActivity.this).create();
                    alerta.setTitle("Respuesta");
                    alerta.setMessage("Respuesta incorrecta");
                    alerta.show();

                    puntaje = puntaje - 5;
                    puntajeAcumulado.setText(puntaje.toString());

                    obtenerPreguntaRandom();
                }
            }
        });


        falso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarRespuesta(false)){
                    AlertDialog alerta = new AlertDialog.Builder(MainActivity.this).create();
                    alerta.setTitle("Respuesta");
                    alerta.setMessage("Respuesta correcta!!!");
                    alerta.show();

                    puntaje = puntaje + 5;
                    puntajeAcumulado.setText(puntaje.toString());

                    obtenerPreguntaRandom();
                } else{
                    AlertDialog alerta = new AlertDialog.Builder(MainActivity.this).create();
                    alerta.setTitle("Respuesta");
                    alerta.setMessage("Respuesta incorrecta");
                    alerta.show();

                    puntaje = puntaje - 5;
                    puntajeAcumulado.setText(puntaje.toString());

                    obtenerPreguntaRandom();
                }
            }
        });

    }

    private void generarPreguntas(){
        preguntas = new HashMap<String, Boolean>();

        preguntas.put("1 + 1 es 3", false);
        preguntas.put("Google tiene una oficina en Asunción", false);
        preguntas.put("Este es el curso de Android ATC", true);
    }

    /**
     * La clase Object: Todas las clases son en realidad subclases de una clase más amplia: la clase Object.
     * Esta clase incluye todos los objetos.
     * Por lo tanto siempre es posible colocar cualquier objeto en donde se espera un expresión de tipo Object.
     * Por ejemplo:
     *     Object o1= "Hola";
     *     Object o2= new TextReader("datos.txt");
     *     Object o3= new Nodo(3, "hola", null, null);
     *     Object o4= new Box( ... );
     */
    private void obtenerPreguntaRandom(){
        //Obtenemos una pregunta randomica
        Object[] arrayDePreguntas = preguntas.keySet().toArray();
        Object key = arrayDePreguntas[new Random().nextInt(arrayDePreguntas.length)];

        Log.d("Pregunta", key + ". Respuesta: " + preguntas.get(key));

        respuestaCorrecta = (Boolean)preguntas.get(key);
        preguntaTextView.setText(key.toString());
    }

    private boolean verificarRespuesta(boolean respuesta){
        if(respuestaCorrecta == respuesta)
            return true;
        else
            return false;

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
