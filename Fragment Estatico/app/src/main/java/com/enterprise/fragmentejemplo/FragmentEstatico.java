package com.enterprise.fragmentejemplo;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEstatico extends Fragment {


    public FragmentEstatico() {
        // Required empty public constructor
    }


    /**
     * Layout Inflater es un objeto especial el cual convierte las vistas XML en objetos Java
     * En actividades usamos setContentView y con fragments vamos a usar LayoutInflater
     *
     * The inflate() method takes three parameters: The id of a layout XML file (inside R.layout),
     * a parent ViewGroup into which the fragment's View is to be inserted, and a third boolean
     * telling whether the fragment's View as inflated from the layout XML file should be inserted
     * into the parent ViewGroup. In this case we pass false because the View will be attached to
     * the parent ViewGroup elsewhere, by some of the Android code we call
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate es el metodo el cual nos va a devolver un objeto Java correspondiente al XML que
        // le pasamos como parametro
        return inflater.inflate(R.layout.fragment_fragment_estatico, container, false);
    }


}
