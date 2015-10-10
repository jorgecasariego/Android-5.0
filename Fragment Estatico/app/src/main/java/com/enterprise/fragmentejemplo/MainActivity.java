package com.enterprise.fragmentejemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
    Pasos para ejemplo de fragment estatico
    1. Crear Fragment utilizando las opciones de Android Studio
    2. Asociamos el fragment con el layout en onCreateView
    3. Necesitamos insertar ahora ese fragment creado en la actividad ya sea por codigo o por XML
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
