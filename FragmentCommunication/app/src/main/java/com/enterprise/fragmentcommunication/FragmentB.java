package com.enterprise.fragmentcommunication;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {
    private TextView mensaje;
    private ImageView imagen;

    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mensaje = (TextView) getActivity().findViewById(R.id.mensaje);
        imagen = (ImageView) getActivity().findViewById(R.id.imagen);
    }

    public void cambiarMensaje(int data){
        mensaje.setText("El contador es " + data);

        switch (data){
            case 1:
                imagen.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.menu1));
                break;
            case 2:
                imagen.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.menu2));
                break;
            case 3:
                imagen.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.menu3));
                break;
        }
    }
}
