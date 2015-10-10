package com.enterprise.fragmentdinamico;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Pasos para ejemplo de fragment estatico
 * 1. Crear Fragment utilizando las opciones de Android Studio
 * 2. Asociamos el fragment con el layout en onCreateView
 * 3. Obtenemos una referencia al FragmentManager
 * 4. Comenzamos una transaccion
 * 5. Creamos un fragment para insertar luego en la actividad
 * 6. Añadimos un fragment a traves del fragment transaction
 * 7. Hacemos el commit del fragment
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        FragmentDinamico fragmentDinamico = new FragmentDinamico();

        /**
         * Parametros
         * 1. donde queremos añadir el fragment
         * 2. pasamos el fragment que queremos añadir a la actividad
         * 3. Tag para relacionar luego al fragment
         */
        transaction.add(R.id.my_relative_layout, fragmentDinamico, "MiFragmentDinamico");

        transaction.commit();


    }
}
