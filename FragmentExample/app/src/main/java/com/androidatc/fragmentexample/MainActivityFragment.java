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

    /**
     * What does LayoutInflater in Android do?
     * LayoutInflater class is used to instantiate layout XML file into its corresponding View objects.
     * In activity we use setContentView and with fragments we use LayoutInflater
     *
     * The inflate() method takes three parameters: The id of a layout XML file (inside R.layout),
     * a parent ViewGroup into which the fragment's View is to be inserted, and a third boolean
     * telling whether the fragment's View as inflated from the layout XML file should be inserted
     * into the parent ViewGroup. In this case we pass false because the View will be attached to
     * the parent ViewGroup elsewhere, by some of the Android code we call
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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
