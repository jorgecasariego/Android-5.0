package com.androidatc.materialdesignejemplo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;

    private ArrayList<Marcacion> marcacionesLista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);

        //Cambiamos el titulo del Action Bar
        //toolbar.setTitle("Material Design - Android ATC");
        setSupportActionBar(toolbar);

        marcacionesLista = new ArrayList<Marcacion>();

        Marcacion m1 = new Marcacion(1, "Jorge", "Entrada", "08:00");
        Marcacion m2 = new Marcacion(1, "Jorge", "Salida", "16:00");
        Marcacion m3 = new Marcacion(2, "Juan", "Entrada", "09:00");
        Marcacion m4 = new Marcacion(2, "Juan", "Salida", "15:30");
        Marcacion m5 = new Marcacion(3, "Diego", "Entrada", "09:00");
        Marcacion m6 = new Marcacion(3, "Diego", "Salida", "15:30");
        Marcacion m7 = new Marcacion(4, "Martin", "Entrada", "09:00");
        Marcacion m8 = new Marcacion(4, "Martin", "Salida", "15:30");

        marcacionesLista.add(m1);
        marcacionesLista.add(m2);
        marcacionesLista.add(m3);
        marcacionesLista.add(m4);
        marcacionesLista.add(m5);
        marcacionesLista.add(m6);
        marcacionesLista.add(m7);
        marcacionesLista.add(m8);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //A pesar del tamaño de la pantalla, asegurar que los elementos se vean siempre igual.
        mRecyclerView.setHasFixedSize(true);

        //Configuramos la animación por defecto
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Ahora le pasamos nuestro dataset y la forma en que se vera cada fila.
        mRecyclerView.setAdapter(new MarcadorAdapter(marcacionesLista, R.layout.row));

        //Importante, ahora podemos indicar si mostrar los elementos como fila o como grilla, en este
        //caso setLayoutManager() esta  configurado como grilla de una columna, es por es eso que se ve
        //como fila, recordar que el mismo resultado se obtiene con
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        mRecyclerView.setLayoutManager(layoutManager);
    }

}
