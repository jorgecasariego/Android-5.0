package com.androidatc.ejercicio1;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button boton1;
    Button boton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1 = (Button)findViewById(R.id.fragment1_button);
        boton2 = (Button)findViewById(R.id.fragment2_button);

        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //Creamos fragment dinamicamente dependiendo de que boton se pulso
        Fragment fragmento = null;

        switch (v.getId()){
            case R.id.fragment1_button:
                fragmento = new Fragment1();
                break;
            case R.id.fragment2_button:
                fragmento = new Fragment2();
                break;
        }

        if(fragmento != null){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.place_holder, fragmento);
            ft.commit();
        }

    }
}
