package com.enterprise.customviewindrawer;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class VentanaPersonalizadaFragment extends Fragment {

    //the custom view
    private AndroidATCView myView;

    public VentanaPersonalizadaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ventana_personalizada, container, false);

        //get reference to the custom view
        myView = (AndroidATCView) rootView.findViewById(R.id.view);
        myView.setSquareColor(Color.BLUE);
        myView.setLabelColor(Color.YELLOW);
        myView.setSquareText("Presioname");
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setSquareColor(Color.GREEN);
                myView.setLabelColor(Color.MAGENTA);
                myView.setSquareText("Android ATC");
            }
        });

        return rootView;
    }


}
