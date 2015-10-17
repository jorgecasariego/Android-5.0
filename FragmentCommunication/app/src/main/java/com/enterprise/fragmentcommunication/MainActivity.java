package com.enterprise.fragmentcommunication;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void respond(int data) {
        FragmentManager manager = getFragmentManager();
        FragmentB fragmentB = (FragmentB) manager.findFragmentById(R.id.fragment_b);

        fragmentB.cambiarMensaje(data);


    }
}
