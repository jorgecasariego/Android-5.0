package com.androidatc.fragmentlistcolores;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    ImageView imagenColores;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_fragment2, container);
        imagenColores = (ImageView)vista.findViewById(R.id.imageView);

        return vista;
    }

    public void setImageViewBackground(int color){
        if(imagenColores != null){
            imagenColores.setBackgroundColor(color);
        }
    }


}
