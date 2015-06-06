package com.androidatc.detailfragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    TextView texto;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        return view;
    }

    //LLamamos a este metodo cuando se pulsa un boton desde ListFragment
    public void setTexto(String item){
        texto = (TextView)getView().findViewById(R.id.detail_tv);
        texto.setText(item);
    }


}
