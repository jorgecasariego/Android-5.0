package com.enterprise.fragmentcommunication;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment implements View.OnClickListener {
    private Button clickButton;
    private int contador = 0;
    private Communicator comm;

    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        comm = (Communicator) getActivity() ;
        clickButton = (Button) getActivity().findViewById(R.id.click);
        clickButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        contador++;

        comm.respond(contador);
    }
}
