package com.androidatc.fragmentexample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    //3. Declaramos la vista que se encuentra en el fragment
    TextView tvFoo;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //1. Definimos el archivo xml para el fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //2. Manejo de configuraciones de la vista
        //4. Asociamos la vista
        tvFoo = (TextView)view.findViewById(R.id.textView1);

        //5. Editamos el Hello World
        tvFoo.setText("Hola mundo de Fragments");


        return view;
    }
}
