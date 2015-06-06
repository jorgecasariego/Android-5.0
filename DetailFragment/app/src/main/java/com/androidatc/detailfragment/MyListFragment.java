package com.androidatc.detailfragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends Fragment implements View.OnClickListener{

    //Declaramos las vistas
    Button android;
    Button ios;
    Button windows;

    //Declaramos la interfaz para pasar mensajes
    private  Communicator communicator;

    public MyListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //Verificamos si la actividad es una instancia de communicator
        if(activity instanceof Communicator){
            communicator = (Communicator)activity;
        } else {
            throw new ClassCastException(activity.toString() + " debe implementar MyListFragment.Communicator");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragment, container, false);

        //Inicializamos las vistas
        android = (Button)view.findViewById(R.id.android_button);
        ios = (Button)view.findViewById(R.id.ios_button);
        windows = (Button)view.findViewById(R.id.windows_button);

        //Manejamos los eventos de click

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.android_button:
                updateFragment("Android");
                break;
            case R.id.ios_button:
                updateFragment("iOS");
                break;
            case R.id.windows_button:
                updateFragment("Windows");
                break;
        }
    }

    private void updateFragment(String os_name){
        communicator.Message(os_name);
    }

    //Creamos una interfaz para pasarle el SO cuando pulse click sobre alguno de los botones
    public interface Communicator{
        public void Message(String os_name);
    }
}
