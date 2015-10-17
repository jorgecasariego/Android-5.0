package com.enterprise.fragmentestaticodinamico;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button rojoButton;
    private Button blancoButton;
    private Button azulButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rojoButton = (Button) findViewById(R.id.rojo);
        blancoButton = (Button) findViewById(R.id.blanco);
        azulButton = (Button) findViewById(R.id.azul);

        rojoButton.setOnClickListener(this);
        blancoButton.setOnClickListener(this);
        azulButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        switch (v.getId()){
            case R.id.rojo:
                fragment = new FragmentRojo();
                break;
            case R.id.blanco:
                fragment = new FragmentBlanco();
                break;
            case R.id.azul:
                fragment = new FragmentAzul();
                break;
        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contenedor, fragment, "");
        transaction.commit();
    }
}
